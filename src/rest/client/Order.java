package rest.client;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/* 
 * dict['orderId'] = self.orderId
 * dict['dateOrder'] = self.dateOrder
 * dict['book_id'] = self.book_id
 * dict['user_id'] = self.user_id
 * dict['price'] = self.price 
 */

@SuppressWarnings("serial")
@JsonRootName(value = "order")
public class Order implements Serializable{
	
	@JsonProperty("orderId")
	private Integer orderId;
	
	@JsonProperty("dateOrder")
	private String dateOrder;
	
	@JsonProperty("book_id")
	private Integer book_id;
	
	@JsonProperty("user_id")
	private Integer user_id;
	
	@JsonProperty("price")
	private float price;

	@JsonProperty("orderId")
	public void setorderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public int getorderId() {
		return this.orderId;
	}
	
	@JsonProperty("dateOrder")
	public void setdateOrder(String dateOrder) {
		this.dateOrder = dateOrder;
	}
	
	public String getdateOrder() {
		return this.dateOrder;
	}
	
	@JsonProperty("book_id")
	public Integer getbookId() {
		return book_id;
	}

	public void setbookId(Integer book_id) {
		this.book_id = book_id;
	}
	
	@JsonProperty("user_id")
	public int getuserId() {
		return user_id;
	}

	public void setuserId(int user_id) {
		this.user_id = user_id;
	}
	
	@JsonProperty("price")
	public float getprice() {
		return price;
	}

	public void setprice(float price) {
		this.price = price;
	}
}
