package demo.small.webshop.model.dto;

import java.util.List;

import demo.small.webshop.model.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FindOrders extends PageData {
	
	private List<Order> orders;
	
}
