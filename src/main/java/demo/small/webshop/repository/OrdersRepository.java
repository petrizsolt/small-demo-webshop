package demo.small.webshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.small.webshop.model.entity.Order;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {
	
	 Page<Order> findAll(Pageable pageable);
	 
	 Page<Order> findAllByDescriptionContains(String name, Pageable pageable);
}
