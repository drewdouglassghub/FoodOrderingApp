package Spring.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@Column(unique = true)
	private String username;
	private String userpassword;
	private boolean enabled;
	
	public User() {
		super();
	}
	
	public User(String username, String userpassword) {
		super();

		this.username = username;
		this.userpassword = userpassword;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", userpassword=" + userpassword + "]";
	}
	
	
	
}
