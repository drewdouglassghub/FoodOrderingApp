package Spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Spring.beans.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long>{
	
}
