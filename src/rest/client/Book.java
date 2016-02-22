package rest.client;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "book")
public class Book {
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("user_id")
	private Integer userID;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("author")
	private String author;
	
	@JsonProperty("edition")
	private String edition;
	
	@JsonProperty("sold")
	private Boolean sold;

	@JsonProperty("bidding_allowed")
	private Boolean biddingAllowed;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("price")
	private Double price;
	
	@JsonProperty("latitude")
	private Double latitude;
	
	@JsonProperty("longitude")
	private Double longitude;
	
	@JsonProperty("rent")
	private Boolean rentAllowed;

	@JsonProperty("sell")
	private Boolean saleAllowed;
	
	@JsonProperty("minimum_period")
	private Integer minimumRentPeriod;
	
	@JsonProperty("maximum_period")
	private Integer maximumRentPeriod;
	
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("user_id")
	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("author")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@JsonProperty("edition")
	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	@JsonProperty("sold")
	public Boolean getSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}

	@JsonProperty("bidding_allowed")
	public Boolean getBiddingAllowed() {
		return biddingAllowed;
	}

	public void setBiddingAllowed(Boolean biddingAllowed) {
		this.biddingAllowed = biddingAllowed;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@JsonProperty("latitude")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@JsonProperty("longitude")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@JsonProperty("rent")
	public Boolean getRentAllowed() {
		return rentAllowed;
	}

	public void setRentAllowed(Boolean rentAllowed) {
		this.rentAllowed = rentAllowed;
	}

	@JsonProperty("sell")
	public Boolean getSaleAllowed() {
		return saleAllowed;
	}

	public void setSaleAllowed(Boolean saleAllowed) {
		this.saleAllowed = saleAllowed;
	}

	@JsonProperty("minimum_period")
	public Integer getMinimumRentPeriod() {
		return minimumRentPeriod;
	}

	public void setMinimumRentPeriod(Integer minimumRentPeriod) {
		this.minimumRentPeriod = minimumRentPeriod;
	}

	@JsonProperty("maximum_period")
	public Integer getMaximumRentPeriod() {
		return maximumRentPeriod;
	}

	public void setMaximumRentPeriod(Integer maximumRentPeriod) {
		this.maximumRentPeriod = maximumRentPeriod;
	}
}
