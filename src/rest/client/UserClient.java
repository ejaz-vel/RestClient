package rest.client;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("deprecation")
public class UserClient {
	private static final String userResource = "http://52.27.180.51/BookStore/v1.0/User/";

	private String getMD5(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(password.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public User getUser(int id) throws ClientProtocolException, IOException {
		String uri = userResource + String.valueOf(id);
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(uri);
		HttpResponse response = client.execute(get);
		if (response.getStatusLine().getStatusCode() != 200) {
			return null;
		}
		String result = EntityUtils.toString(response.getEntity());
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		User user = mapper.readValue(result, User.class);
		return user;
	}
	
	public User login(String loginID, String password) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		StringBuilder sb = new StringBuilder();
		sb.append(userResource);
		sb.append("?");
		if (loginID.contains("@")) {
			sb.append("email_address=");
			sb.append(loginID);
		} else {
			sb.append("phone=");
			sb.append(loginID);
		}
		sb.append("&password_hash=");
		sb.append(getMD5(password));
		HttpGet get = new HttpGet(sb.toString());
		
		HttpResponse response = client.execute(get);
		if (response.getStatusLine().getStatusCode() != 200) {
			return null;
		}
		String result = EntityUtils.toString(response.getEntity());
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		User user = mapper.readValue(result, User.class);
		return user;
	}
	
	@SuppressWarnings("resource")
	public User signUp(String emailID, String password, String address, String phone)
			throws ClientProtocolException, IOException {
		String userName = emailID.substring(0, emailID.indexOf("@"));
		String passwordHash = getMD5(password);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("user_name", userName);
		jsonObj.put("email_address", emailID);
		jsonObj.put("email_address", emailID);
		jsonObj.put("password_hash", passwordHash);
		jsonObj.put("address", address);
		jsonObj.put("phone", phone);

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(userResource);
		System.out.println(post.getURI());
		post.setHeader("Content-type", "application/json");
		post.setHeader("Accept", "application/json");
		StringEntity entity = new StringEntity(jsonObj.toString(), ContentType.create("application/json"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		if (response.getStatusLine().getStatusCode() != 201) {
			return null;
		}
		String result = EntityUtils.toString(response.getEntity());
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		User user = mapper.readValue(result, User.class);
		return user;
	}
}
