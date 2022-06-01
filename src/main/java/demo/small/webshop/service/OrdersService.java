package demo.small.webshop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import demo.small.webshop.model.dto.OrderRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import demo.small.webshop.model.dto.FindOrders;
import demo.small.webshop.model.dto.OrderResponse;
import demo.small.webshop.model.entity.Order;
import demo.small.webshop.model.entity.Product;
import demo.small.webshop.repository.OrdersRepository;
import demo.small.webshop.repository.ProductsRepository;

@Service
public class OrdersService {
	private final ProductsRepository productsRepository;
	private final OrdersRepository ordersRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public OrdersService(ProductsRepository productsRepository, OrdersRepository ordersRepository,
			ModelMapper modelMapper) {
		this.productsRepository = productsRepository;
		this.ordersRepository = ordersRepository;
		this.modelMapper = modelMapper;
	}

	@Transactional
	public ResponseEntity<OrderResponse> createOrder(List<OrderRequest> request)  {
		//List<Product> productsRequired = productsRepository.findAllByNameIn(request.stream().map(r -> r.getName()).collect(Collectors.toList()));
		ResponseEntity<OrderResponse> resp = makeOrder(request);
		if(!resp.getStatusCode().equals(HttpStatus.OK))
			return resp;
		
		Order order = modelMapper.map(resp.getBody(), Order.class);
		order.setOrderDate(LocalDateTime.now());
		ordersRepository.save(order);
		return resp;
	}
	
	@Transactional
	public ResponseEntity<OrderResponse> modifyOrder(Long id, List<OrderRequest> request) {
		Long oldOrderId = productsRepository.findById(id).get().getId();
		ResponseEntity<OrderResponse> resp = makeOrder(request);
		
		if(!resp.getStatusCode().equals(HttpStatus.OK))
			return resp;
		
		Order modifiedOrder = modelMapper.map(resp.getBody(), Order.class);
		modifiedOrder.setId(oldOrderId);
		modifiedOrder.setOrderDate(LocalDateTime.now());
		ordersRepository.save(modifiedOrder);
		return resp;
	}

	public ResponseEntity<FindOrders> getAllOrders(Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Order> orders = ordersRepository.findAll(paging);
		return ResponseEntity.ok(getFoundOrders(orders));
	}

	public ResponseEntity<FindOrders> getAllOrdersByProduct(String productName, Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Order> orders = ordersRepository.findAllByDescriptionContains(productName, paging);
		return ResponseEntity.ok(getFoundOrders(orders));
	}
	
	private FindOrders getFoundOrders(Page<Order> orders) {
		FindOrders foundOrders = new FindOrders();
		foundOrders.setOrders(orders.getContent());
		foundOrders.setCurrentPage(orders.getNumber());
		foundOrders.setTotalElements(orders.getTotalPages());
		foundOrders.setTotalPages(orders.getTotalPages());
		return foundOrders;
	}
	
	@Transactional
	public ResponseEntity<?> deleteOrderById(Long id) {
		ordersRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	private ResponseEntity<OrderResponse> makeOrder(List<OrderRequest> request) {
		OrderResponse resp = new OrderResponse();
		String description = "";
		Double cost = 0D;
		for(OrderRequest req: request) {
			Optional<Product> product = productsRepository.findByName(req.getName());
			if(!product.isPresent())
				return ResponseEntity.notFound().build();
				
			description += product.get().getName().concat(" ");
			cost += product.get().getPrice() * req.getQuantiy();
		}
		resp.setDescription(description.trim());
		resp.setCost(cost);
		return ResponseEntity.ok(resp);
	}
}
