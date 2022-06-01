package demo.small.webshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.small.webshop.model.entity.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
	
	 Page<Product> findAll(Pageable pageable);
	 
	 Optional<Product> findByName(String name);
	 
	 List<Product> findAllByNameIn(List<String> name);
}
