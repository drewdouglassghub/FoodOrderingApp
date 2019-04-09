package Spring.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Spring.beans.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{


	
}
