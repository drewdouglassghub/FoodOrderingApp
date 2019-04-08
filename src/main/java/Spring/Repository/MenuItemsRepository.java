package Spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Spring.beans.MenuItems;

public interface MenuItemsRepository extends JpaRepository<MenuItems, Long>{
	
	public List<MenuItems> findByItemDepartment(long itemDepartment);
}
