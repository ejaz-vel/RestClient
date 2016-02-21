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
	
	@JsonProperty("latitude")
	private Double latitude;
	
	@JsonProperty("longitude")
	private Double longitude;
	
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
}
