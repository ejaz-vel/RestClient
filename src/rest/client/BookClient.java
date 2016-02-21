package rest.client;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookClient {

	private static final String bookResource = "http://52.27.180.51/BookStore/v1.0/Book/";

	public Set<Book> search(String query) throws ClientProtocolException, IOException {
		Set<Book> books = new HashSet<>();
		HttpClient client = HttpClientBuilder.create().build();
		query = query.replaceAll(" ", "%20");

		// Use the query to search for book names
		String uri = bookResource + "?name=" + query;
		HttpGet get = new HttpGet(uri);
		HttpResponse response = client.execute(get);
		if (response.getStatusLine().getStatusCode() == 200) {
			String result = EntityUtils.toString(response.getEntity());
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
			books.addAll((Collection<? extends Book>) mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(Set.class, Book.class)));
		}

		// Use the query to search for book author
		uri = bookResource + "?author=" + query;
		get = new HttpGet(uri);
		response = client.execute(get);
		if (response.getStatusLine().getStatusCode() == 200) {
			String result = EntityUtils.toString(response.getEntity());
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
			books.addAll((Collection<? extends Book>) mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(Set.class, Book.class)));
		}
		return books;
	}
	
//	public Book listBook(Integer user_id, String name, String author, String edition, String description,
//			Boolean biddingAllowed, Double latitude, Double longitude) {
//		JSONObject jsonObj = new JSONObject();
//		jsonObj.put("user_id", String.valueOf(user_id));
//		jsonObj.put("name", name);
//		jsonObj.put("author", author);
//		jsonObj.put("edition", edition);
//		jsonObj.put("description", description);
//		if (biddingAllowed) {
//			jsonObj.put("bidding_allowed", true);
//		}
//		
//		if (latitude != null && longitude != null) {
//			jsonObj.put("latitude", String.valueOf(latitude));
//			jsonObj.put("longitude", String.valueOf(longitude));
//		}
//		
//		HttpClient client = HttpClientBuilder.create().build();
//		
//	}
}
