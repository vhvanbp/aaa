package entity.others;

public class TableCount {
	//Region - properties
	private String tableID; 
	private int quantity;
	//EndRegion
	
	//Region - constructors
	public TableCount(String tableID, int quantity) {
		this.tableID = tableID;
		this.quantity = quantity;
	}

	public TableCount() {
	
	}
	//EndRegion

	//Region - setter getter
	public String getTableID() {
		return tableID;
	}
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	//EndRegion

	//Region - toString
	@Override
	public String toString() {
		return "TableCount [tableID=" + tableID + ", quantity=" + quantity + "]";
	}	
	//EndRegion
}
