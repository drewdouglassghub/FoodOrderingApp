package Spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Spring.beans.OrderItems;
import Spring.beans.Orders;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long>{

	public List<OrderItems> findByOrderId(Orders id);
	OrderItems findById(long id);
	
}
