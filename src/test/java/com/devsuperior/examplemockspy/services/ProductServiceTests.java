package com.devsuperior.examplemockspy.services;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.examplemockspy.dto.ProductDTO;
import com.devsuperior.examplemockspy.entities.Product;
import com.devsuperior.examplemockspy.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
@ExtendWith(SpringExtension.class)
public class ProductServiceTests {
 
	
	@InjectMocks
	private ProductService productService;
	
	@Mock
	private ProductRepository repository;
	
	private Product product;
	private ProductDTO productDTO;
	
	
	private Long existingId,nonExistingId;
	
	@BeforeEach
	void setup() throws Exception{
		
		
		existingId = 1L;
		nonExistingId = 2L;
		
		product = new Product(1L, "Playstation", 2000.00);
		productDTO = new ProductDTO(product);
		
		Mockito.when(repository.save(any())).thenReturn(product);
		
		Mockito.when(repository.getReferenceById(existingId)).thenReturn(product);
		Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
		
	}
	
	
	@Test// Deve inserir e retornar produto dto quando valido...
	public void insertShouldReturnProductDTOWhenValidDate() {
		
		ProductService serviceSpy = Mockito.spy(productService);
		
		Mockito.doNothing().when(serviceSpy).validateData(productDTO);
		ProductDTO result = serviceSpy.insert(productDTO);
		
		Assertions.assertNotNull(result);//testar se retorna vazio
	    Assertions.assertEquals(result.getName(), "Playstation");// ver se o nome e igual a Playstation
	}

}
