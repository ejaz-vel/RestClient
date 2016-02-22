package rest.client;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserClient {
	private static final String userResource = "http://52.36.182.190/BookStore/v1.0/User/";

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

	public User getUser(int id) throws IOException {
		String uri = userResource + String.valueOf(id);
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
			      .url(uri)
			      .build();
		Response response = client.newCall(request).execute();
		if (response.code() != 200) {
			return null;
		}
		String result = response.body().string();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		User user = mapper.readValue(result, User.class);
		return user;
	}
	
	public User login(String loginID, String password) throws IOException {
		OkHttpClient client = new OkHttpClient();
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
		Request request = new Request.Builder()
			      .url(sb.toString())
			      .build();
		Response response = client.newCall(request).execute();
		if (response.code() != 200) {
			return null;
		}
		String result = response.body().string();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		User user = mapper.readValue(result, User.class);
		return user;
	}
	
	public User signUp(String emailID, String password, String address, String phone) throws IOException {
		String userName = emailID.substring(0, emailID.indexOf("@"));
		String passwordHash = getMD5(password);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("user_name", userName);
		jsonObj.put("email_address", emailID);
		jsonObj.put("email_address", emailID);
		jsonObj.put("password_hash", passwordHash);
		jsonObj.put("address", address);
		jsonObj.put("phone", phone);

		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObj.toString());
		Request request = new Request.Builder()
			      .url(userResource)
			      .post(body)
			      .build();
		Response response = client.newCall(request).execute();
		if (response.code() != 201) {
			return null;
		}
		String result = response.body().string();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		User user = mapper.readValue(result, User.class);
		return user;
	}
	
	public static void main(String args[]) {
		UserClient uc = new UserClient();
		try {
			uc.login("4126362590", "ejazveljee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}