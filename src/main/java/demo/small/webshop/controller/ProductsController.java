package demo.small.webshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.small.webshop.model.dto.FindProducts;
import demo.small.webshop.model.dto.SaveProduct;
import demo.small.webshop.service.ProductsService;

@Validated
@RestController
@CrossOrigin
@RequestMapping("/rest/products/")
public class ProductsController {
	private final ProductsService productsService;

	@Autowired
	public ProductsController(ProductsService productsService) {
		this.productsService = productsService;
	}
	
	@GetMapping("get/all/{page}/{size}")
	public ResponseEntity<FindProducts> getAllProducts(
			@PathVariable("page") Integer page,
			@PathVariable("size") Integer size) {
		return productsService.getAllProducts(page,size);
	}
	
	@PostMapping("save")
	public ResponseEntity<?> saveProducts(
			@Valid @RequestBody SaveProduct product) {
		return productsService.saveProduct(product);
	}
	
	@PutMapping("modify/by/{id}")
	public ResponseEntity<?> modifyProducts(
			@PathVariable("id") Long id, 
			@Valid @RequestBody SaveProduct product) {
		return productsService.modifyProduct(id, product);
	}
	
	@DeleteMapping("delete/by/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
		return productsService.deleteProductById(id);
	}
	
}
