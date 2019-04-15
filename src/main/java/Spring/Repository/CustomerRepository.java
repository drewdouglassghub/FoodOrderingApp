package Spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Spring.beans.User;

@Repository
public interface CustomerRepository extends JpaRepository<User, Long>{
	
	User findByUserName(String username);
}