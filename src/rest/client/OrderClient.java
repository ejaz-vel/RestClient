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

/* 
 * dict['orderId'] = self.orderId
 * dict['dateOrder'] = self.dateOrder
 * dict['book_id'] = self.book_id
 * dict['user_id'] = self.user_id
 * dict['price'] = self.price 
 */

public class OrderClient {

	private static final String OrderResource = "http://127.0.0.1:8080/BookStore/v1.0/Orders/";
	
	public Order postOrder(int orderid, String orderdate, int book_id,
			                int user_id, float price) throws IOException {

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("orderId", orderid);
		jsonObj.put("dateOrder", orderdate);
		jsonObj.put("book_id", book_id);
		jsonObj.put("user_id", user_id);
		jsonObj.put("price", price);

		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObj.toString());
		Request request = new Request.Builder()
			      .url(OrderResource)
			      .post(body)
			      .build();
		
		System.out.println("Sending Order to server");
		Response response = client.newCall(request).execute();
		if (response.code() != 201) {
			System.out.println("Response code = " + response.code());
			return null;
		}
		String result = response.body().string();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		Order order =  mapper.readValue(result, Order.class);
		return order;
	}
	
	public static void main(String args[]) {
		
		OrderClient order_client = new OrderClient();
		Order order = new Order();
		
		/*
		order.setbookId("bookId");
		order.setorderId(1);
		order.setprice("$100");
		order.setuserId("123");
		order.setdateOrder(12345);
		*/
		
		try {
			order_client.postOrder(1,"OrderDate", 123, 12345, 100);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
