package Spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Spring.beans.Cart;
import Spring.beans.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findByUserId(User userId);
	
}
