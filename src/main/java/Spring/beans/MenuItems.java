package Spring.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="menu_items")
public class MenuItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name="ITEM_NAME")
	private String itemName;
	@Column(name="ITEM_PRICE")
	private double itemPrice;
	@Column(name="ITEM_IMAGE")
	private String itemPath;
	@Column(name="ITEM_DESCRIPTION")
	private String itemDescription;
	@Column(name="DEPARTMENT")
	private int itemDepartment;
	
	public MenuItems() {
		super();
	}
	
	public MenuItems(long id, String itemName, double itemPrice, String itemDescription, int itemDepartment) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemDescription = itemDescription;
		this.itemDepartment = itemDepartment;
	}
	
	public MenuItems(long id, String itemName, double itemPrice, String itemPath, String itemDescription, int itemDepartment) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemPath = itemPath;
		this.itemDescription = itemDescription;
		this.itemDepartment = itemDepartment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemPath() {
		return itemPath;
	}

	public void setItemPath(String itemPath) {
		this.itemPath = itemPath;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public int getItemDepartment() {
		return itemDepartment;
	}

	public void setItemDepartment(int itemDepartment) {
		this.itemDepartment = itemDepartment;
	}
	
	@Override
	public String toString() {
		return "MenuItem [id = " + id + ", itemName = " + itemName + ", itemPrice = $" + itemPrice + ", itemPath = " + itemPath + ", itemDescription = " + itemDescription + ", + itemDepartment = " + itemDepartment + "]";
	}
}
