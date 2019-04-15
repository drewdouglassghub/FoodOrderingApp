package Spring.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_cart")
public class Cart {

	@Id
	@Column(name="cart_id")
	private long cartId;
	@Column(name="prod_name")
	private String productName;
	@Column(name="prod_qty")
	private int productQty;
	@Column(name="customer_id")
	private long custId;
	
	public Cart() {
		super();
	}

	public Cart(long cartId, String productName, int productQty, long custId) {
		super();
		this.cartId = cartId;
		this.productName = productName;
		this.productQty = productQty;
		this.custId = custId;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", productName=" + productName + ", productQty=" + productQty + ", custId="
				+ custId + "]";
	}
	
	
	
	
	
}
