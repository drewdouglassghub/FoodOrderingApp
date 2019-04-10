package Spring.beans;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "CUSTOMER_FIRSTNAME")
	private String firstName;
	@Column(name = "CUSTOMER_LASTNAME")
	private String lastName;
	@Column(name = "CUSTOMER_VISITDATE")
	private Date visitDate;
	@Column(name = "CUSTOMER_EMAIL")
	private String email;
	@Column(name = "CUSTOMER_PHONE")
	private int phoneNumber;
	
	private String auth;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	//No ID constructor
	public Customer(String firstName, String lastName, Date visitDate, String email, int phoneNumber, String auth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.visitDate = visitDate;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.auth = auth;
	}


	//All fields constructor
	public Customer(long id, String firstName, String lastName, Date visitDate, String email, int phoneNumber, String auth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.visitDate = visitDate;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.auth = auth;
	}



	public String getAuth() {
		return auth;
	}


	public void setAuth(String auth) {
		this.auth = auth;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", visitDate=" + visitDate
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", auth=" + auth + "]";
	}
}