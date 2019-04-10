package Spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Spring.beans.MenuDepartments;

@Repository
public interface MenuDepartmentsRepository extends JpaRepository <MenuDepartments, Long>{

}
