package Spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Spring.beans.MenuDepartments;
import Spring.beans.MenuItems;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItems, Long>{
	
	public List<MenuItems> findByItemDepartment(MenuDepartments id);
}
