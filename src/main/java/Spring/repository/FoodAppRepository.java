package Spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Spring.beans.User;


public interface FoodAppRepository extends JpaRepository<User, String>{

	 User findByUsername(String username);
	
}
