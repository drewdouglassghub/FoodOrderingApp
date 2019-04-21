package Spring.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="promotion")
public class Promotion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PROMOTION_ID")
	private long id;
	@Column(name="PROMOTION_NAME")
	private String name;
	@Column(name="PROMOTION_TYPE")
	private String type;
	@Column(name="PROMOTION_AMOUNT")
	private double amount;
	@Column(name="ACTIVE")
	private String active;
	
	public Promotion() {
		super();
	}
	
	public Promotion(long id, String name, String type, double amount, String active) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.amount = amount;
		this.active = active;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	
}
