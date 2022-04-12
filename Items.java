package udaan;

public class Items {

	private String itemName,id;
	private double itemActualPrice;
	private int itemsOnDeal;
	
	
	/**
	 * @param itemName
	 * @param itemActualPrice
	 * @param itemsOnDeal
	 */
	public Items(String id,String itemName, double itemActualPrice, int itemsOnDeal) {
		this.setId(id);
		this.itemName = itemName;
		this.itemActualPrice = itemActualPrice;
		this.itemsOnDeal = itemsOnDeal;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getItemActualPrice() {
		return itemActualPrice;
	}
	public void setItemActualPrice(double itemActualPrice) {
		this.itemActualPrice = itemActualPrice;
	}
	public int getItemsOnDeal() {
		return itemsOnDeal;
	}
	public void setItemsOnDeal(int itemsOnDeal) {
		this.itemsOnDeal = itemsOnDeal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
