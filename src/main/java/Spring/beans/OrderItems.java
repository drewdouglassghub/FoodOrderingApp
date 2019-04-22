package Spring.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "order_items")
public class OrderItems {
	
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name="ORDER_ID")
	private Orders orderId;
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name="ITEM_ID")
	private MenuItems itemId;
	@Column(name = "QUANTITY")
	private int quantity;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public OrderItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Only item id constructor
	public OrderItems(MenuItems itemId) {
		super();
		this.itemId = itemId;
	}
	

	//All fields constructor
	public OrderItems(Orders orderId, MenuItems itemId, int quantity) {
		super();
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public String getItemName() {
		return itemId.getItemName();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Orders getOrderId() {
		return orderId;
	}

	public void setOrderId(Orders orderId) {
		this.orderId = orderId;
	}

	public MenuItems getItemId() {
		return itemId;
	}

	public void setItemId(MenuItems itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItems [orderId=" + orderId + ", itemId=" + itemId + ", quantity=" + quantity + ", id=" + id + "]";
	}

	
	
	

}
