package Spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Spring.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}
