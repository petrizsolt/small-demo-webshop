package demo.small.webshop.controller;

import java.util.List;

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

import demo.small.webshop.model.dto.FindOrders;
import demo.small.webshop.model.dto.OrderRequest;
import demo.small.webshop.model.dto.OrderResponse;
import demo.small.webshop.service.OrdersService;

@Validated
@RestController
@CrossOrigin
@RequestMapping("/rest/orders/")
public class OrdersController {
	private final OrdersService ordersService;

	@Autowired
	public OrdersController(OrdersService ordersService) {
		this.ordersService = ordersService;
	}
	
	@PostMapping("create")
	public ResponseEntity<OrderResponse> createOrder(
			@Valid @RequestBody List<OrderRequest> request) {
		return ordersService.createOrder(request);
	}
	
	@PutMapping("modify/by/{id}")
	public ResponseEntity<OrderResponse> modifyOrder(
			@PathVariable("id") Long id,
			@Valid @RequestBody List<OrderRequest> request) {
		return ordersService.modifyOrder(id, request);
	}
	
	@GetMapping("get/all/{page}/{size}")
	public ResponseEntity<FindOrders> getAllOrders(
			@PathVariable("page") Integer page, 
			@PathVariable("size") Integer size) {
		return ordersService.getAllOrders(page,size);
	}
	
	@GetMapping("get/all/by/product/{productName}/{page}/{size}")
	public ResponseEntity<FindOrders> getAllProductsByProduct(
			@PathVariable("productName") String productName,												      
			@PathVariable("page") Integer page, 
			@PathVariable("size") Integer size) {
		return ordersService.getAllOrdersByProduct(productName, page, size);
	}
	
	@DeleteMapping("delete/by/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
		return ordersService.deleteOrderById(id);
	}
	
}
