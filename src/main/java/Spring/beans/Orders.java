package Spring.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderid;
	@Column(name = "CUSTOMER_ID")
	private int customerId;
	
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Orders(long id, int customerId) {
		super();
		this.orderid = id;
		this.customerId = customerId;
	}
	
	
	public long getId() {
		return orderid;
	}
	public void setId(long id) {
		this.orderid = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
}
