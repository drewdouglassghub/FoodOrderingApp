package Spring.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;

import Spring.beans.User;


public interface FoodAppRepository extends JpaRepository<User, String>{
	@PersistenceContext 
	EntityManager entityManager;
	 User findByUsername(String username);
	
}
