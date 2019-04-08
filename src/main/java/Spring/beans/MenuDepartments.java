package Spring.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="menu_departments")
public class MenuDepartments {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DEPARTMENT_ID")
	private long id;
	@Column(name="DEPARTMENT_NAME")
	private String departmentName;
	
	public MenuDepartments() {
		super();
	}
	
	public MenuDepartments(long id, String departmentName) {
		super();
		this.id = id;
		this.departmentName = departmentName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Override
	public String toString() {
		return "Department [id = " + id + ", Department Name = " + departmentName + "]";
	}
}
