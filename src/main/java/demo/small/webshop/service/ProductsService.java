package demo.small.webshop.service;

import java.net.URI;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import demo.small.webshop.model.dto.FindProducts;
import demo.small.webshop.model.dto.OrderResponse;
import demo.small.webshop.model.dto.SaveProduct;
import demo.small.webshop.model.entity.Product;
import demo.small.webshop.repository.ProductsRepository;

@Service
public class ProductsService {
	private final ProductsRepository productsRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public ProductsService(ProductsRepository productsRepository, ModelMapper modelMapper) {
		this.productsRepository = productsRepository;
		this.modelMapper = modelMapper;
		OrderResponse order = new OrderResponse();

	}
	
	@Transactional
	public ResponseEntity<?> saveProduct(SaveProduct product) {
		Product productsSave = modelMapper.map(product, Product.class);
		productsSave = productsRepository.save(productsSave);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productsSave.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@Transactional
	public ResponseEntity<?> modifyProduct(Long id, SaveProduct product) {
		Long oldProductId = productsRepository.findById(id).get().getId();
		Product modifiedProduct = modelMapper.map(product, Product.class);
		modifiedProduct.setId(oldProductId);
		productsRepository.save(modifiedProduct);
		return ResponseEntity.ok().build();
	}
	
	@Transactional
	public ResponseEntity<?> deleteProductById(Long id) {
		productsRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<FindProducts> getAllProducts(Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Product> products = productsRepository.findAll(paging);
		FindProducts foundProducts = new FindProducts();
		foundProducts.setProducts(products.getContent());
		foundProducts.setCurrentPage(products.getNumber());
		foundProducts.setTotalElements(products.getTotalPages());
		foundProducts.setTotalPages(products.getTotalPages());		
		return ResponseEntity.ok(foundProducts);
	}
	
	
}
