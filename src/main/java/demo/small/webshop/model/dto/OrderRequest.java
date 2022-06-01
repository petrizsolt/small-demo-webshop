package demo.small.webshop.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
	@NotBlank(message = "Missing field 'name'!")
	private String name;
	
	@Min(1)
	private Integer quantiy;
}
