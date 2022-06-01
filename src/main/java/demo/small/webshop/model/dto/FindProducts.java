package demo.small.webshop.model.dto;

import java.util.List;

import demo.small.webshop.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FindProducts extends PageData {
	
	private List<Product> products;
	
}

