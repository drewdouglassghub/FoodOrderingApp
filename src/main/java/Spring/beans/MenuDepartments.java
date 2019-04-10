package Spring.beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu_departments")
public class MenuDepartments {
	@Id
	@Column(name="department_id")
	private long departmentId;
	@Column(name="department_name")
	private String departmentName;
	
	public MenuDepartments() {
		super();
	}
	
	public MenuDepartments(long id, String departmentName) {
		super();
		this.departmentId = id;
		this.departmentName = departmentName;
	}

	public long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Override
	public String toString() {
		return "Department [id = " + departmentId + ", Department Name = " + departmentName + "]";
	}
}
