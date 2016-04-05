package rest.client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BookClient {

	private static final String bookResource = "http://127.0.0.1:8080/BookStore/v1.0/Book/";
	
	public List<Book> search(String query) throws IOException {
		List<Book> books = new ArrayList<>();
		OkHttpClient client = new OkHttpClient();

		// Use the query to search for book names
		Request request = new Request.Builder()
			      .url(bookResource + "?queryString=" + query)
			      .build();
		
		Response response = client.newCall(request).execute();
		
		if (response.code() == 200) {
			String result = response.body().string();
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
			books.addAll((Collection<? extends Book>) mapper.readValue(result, 
					mapper.getTypeFactory().constructCollectionType(List.class, Book.class)));
		}
		return books;
	}
	
	public Book listBook(Book newBook) throws IOException {
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("user_id", newBook.getUserID());
		jsonObj.put("name", newBook.getName());
		jsonObj.put("edition", newBook.getEdition());
		jsonObj.put("author", newBook.getAuthor());
		jsonObj.put("description", newBook.getDescription());
		jsonObj.put("price", newBook.getPrice());
		jsonObj.put("sell", newBook.getSaleAllowed());
		
		// Optional fields
		if (newBook.getRentAllowed()) {
			jsonObj.put("rent", newBook.getRentAllowed());
			jsonObj.put("minimum_period", newBook.getMinimumRentPeriod());
			jsonObj.put("maximum_period", newBook.getMaximumRentPeriod());
		}
		
		if (newBook.getBiddingAllowed() != null) {
			jsonObj.put("bidding_allowed", newBook.getBiddingAllowed());
		}
		
		if(newBook.getLatitude() != null && newBook.getLongitude() != null) {
			jsonObj.put("latitude", newBook.getLatitude());
			jsonObj.put("longitude", newBook.getLongitude());
		}
		
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObj.toString());
		Request request = new Request.Builder()
			      .url(bookResource)
			      .post(body)
			      .build();
		Response response = client.newCall(request).execute();
		if (response.code() != 201) {
			return null;
		}
		String result = response.body().string();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		Book book =  mapper.readValue(result, Book.class);
		return book;
	}
	
	public static void main(String args[]) {
		BookClient bc = new BookClient();
		try {
//			Book newBook = new Book();
//			newBook.setUserID(3);
//			newBook.setName("Harry Potter and Order Of Phoenix");
//			newBook.setAuthor("Harry Potter");
//			newBook.setEdition("1.0");
//			newBook.setPrice(15.0);
//			newBook.setSaleAllowed(true);
//			newBook.setRentAllowed(false);
//			newBook.setMinimumRentPeriod(4);
//			newBook.setMaximumRentPeriod(20);
//			newBook.setDescription("Harry Potter");
//			newBook.setBiddingAllowed(false);
//			Book book = bc.listBook(newBook);
			
			List<Book> books = bc.search("hellobook");
			System.out.println(books.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
