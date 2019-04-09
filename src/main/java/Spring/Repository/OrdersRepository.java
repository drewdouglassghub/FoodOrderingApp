package Spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Spring.beans.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
