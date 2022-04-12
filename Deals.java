package udaan;

import java.time.LocalDateTime;

public class Deals {

	private String dealName,id;
	private int discountValue;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	
	
	public Deals(String id,String dealName, int discountValue, LocalDateTime startDateTime, LocalDateTime endDateTime) {
		super();
		this.id = id;
		this.dealName = dealName;
		this.discountValue = discountValue;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}
	
	public String getDealName() {
		return dealName;
	}
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	public int getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(int discountValue) {
		this.discountValue = discountValue;
	}
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
