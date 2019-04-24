package Spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Spring.beans.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
	Promotion findByName(String name);
}
