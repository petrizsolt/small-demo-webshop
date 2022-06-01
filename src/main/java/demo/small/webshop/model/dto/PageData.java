package demo.small.webshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageData {
	
	protected Integer currentPage;
	
	protected Integer totalPages;
	
	protected Integer totalElements;
	
}
