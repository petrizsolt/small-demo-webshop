package demo.small.webshop.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveProduct {
	
	@NotBlank(message = "Missing 'name' !")
	private String name;
	
	private String description;
	
	@Min(1)
	@NotNull(message = "Product price missing!")
	private Double price;
}
