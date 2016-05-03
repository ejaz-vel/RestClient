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

	private String bookResource;

	public BookClient(String IPAddress) {
		bookResource = "http://" + IPAddress + "/BookStore/v1.0/Book/";
	}

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
		
		if (newBook.getCondition() == null) {
			jsonObj.put("condition", 3.0);
		} else {
			jsonObj.put("condition", newBook.getCondition());
		}

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
		BookClient bc = new BookClient("52.36.199.35");
		try {
			Book newBook = new Book();
			newBook.setUserID(1);
			newBook.setName("Hadoop Map Reduce");
			newBook.setAuthor("Jim Dean");
			newBook.setEdition("1.0");
			newBook.setPrice(5.0);
			newBook.setSaleAllowed(true);
			newBook.setRentAllowed(false);
			newBook.setMinimumRentPeriod(4);
			newBook.setMaximumRentPeriod(20);
			newBook.setDescription("Brand New");
			newBook.setBiddingAllowed(false);
			newBook.setLatitude(40.446893);
			newBook.setLongitude(-79.933984);
			Book book = bc.listBook(newBook);
			System.out.println(book);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
