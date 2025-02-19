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
import com.devsuperior.examplemockspy.services.exceptions.InvalidDataException;
import com.devsuperior.examplemockspy.services.exceptions.ResourceNotFoundException;

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
	
	
	@Test//Deve retornar uma escecao caso o nome seja em branco ou nulo....
	public void insertShouldRertunrInvalidDataExceptionWhenProductNameIsBlanck() {
		
		productDTO.setName("");
		
		ProductService serviceSpy = Mockito.spy(productService);
		Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateData(productDTO);
		
		Assertions.assertThrows(InvalidDataException.class,()->{
			@SuppressWarnings("unused")
			ProductDTO result = serviceSpy.insert(productDTO);
			
			
			
		});
		
		
	}
	
	
	@Test//Deve retornar uma escecao caso o preco negativo ou nulo
	public void insertShouldRertunrInvalidDataExceptionWhenProductPriceIsNegativeOrZero() {
		
		productDTO.setPrice(-5.0);
		
		ProductService serviceSpy = Mockito.spy(productService);
		Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateData(productDTO);
		
		Assertions.assertThrows(InvalidDataException.class,()->{
			@SuppressWarnings("unused")
			ProductDTO result = serviceSpy.insert(productDTO);
		});
		
		
	}
	
	@Test//atualiza deve retornar produto dto quando id existe e tem validção correta
	public void updateShouldReturnProductDTOWhenIdExistsAndValidData() {
		ProductService serviceSpy = Mockito.spy(productService);
		Mockito.doNothing().when(serviceSpy).validateData(productDTO);
		ProductDTO result = serviceSpy.update(existingId,productDTO);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), existingId);
		
	}
	
	@Test//id existente e o nome do produto e nulo ou em branco
	public void updateShouldReturnInvalidDataExceptionWhenIdExistsAndProductNameIsBlank() {
     productDTO.setName("");
		
		ProductService serviceSpy = Mockito.spy(productService);
		Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateData(productDTO);
		
		Assertions.assertThrows(InvalidDataException.class,()->{
			@SuppressWarnings("unused")
			ProductDTO result = serviceSpy.update(existingId,productDTO);
		});
		
	}
	
	
	
	@Test//id existente e o preco do produto e negativo ou zero
	public void updateShouldReturnInvalidDataExceptionWhenIdExistsAndProductPriceIsNegativeOrZero() {
     productDTO.setPrice(-5.0);
		
		ProductService serviceSpy = Mockito.spy(productService);
		Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateData(productDTO);
		
		Assertions.assertThrows(InvalidDataException.class,()->{
			@SuppressWarnings("unused")
			ProductDTO result = serviceSpy.update(existingId,productDTO);
		});
		
	}
	
	
	@Test// atualiza deve retornar uma excecao ResourceNotFoundException se o id nao existir e validações corretas
	public void updateShouldReturnResourceNotFoundExceptionIdDoesNotExistAndValidData() {

		ProductService serviceSpy = Mockito.spy(productService);
		
		Mockito.doNothing().when(serviceSpy).validateData(productDTO);
		
		Assertions.assertThrows(ResourceNotFoundException.class,()->{
			@SuppressWarnings("unused")
			ProductDTO result = serviceSpy.update(nonExistingId,productDTO);
		});
		
	}
	
	
	@Test//id nao existente e o nome do produto e nulo ou em branco
	public void updateShouldReturnInvalidDataExceptionWhenIdDoesNotExistsAndProductNameIsBlank() {
     productDTO.setName("");
		
		ProductService serviceSpy = Mockito.spy(productService);
		Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateData(productDTO);
		
		Assertions.assertThrows(InvalidDataException.class,()->{
			@SuppressWarnings("unused")
			ProductDTO result = serviceSpy.update(nonExistingId,productDTO);
		});
		
	}
	
	
	
	@Test//id nao existente e o preco do produto e negativo ou zero
	public void updateShouldReturnInvalidDataExceptionWhenIdDoesNotExistsAndProductPriceIsNegativeOrZero() {
     productDTO.setPrice(-5.0);
		
		ProductService serviceSpy = Mockito.spy(productService);
		Mockito.doThrow(InvalidDataException.class).when(serviceSpy).validateData(productDTO);
		
		Assertions.assertThrows(InvalidDataException.class,()->{
			@SuppressWarnings("unused")
			ProductDTO result = serviceSpy.update(nonExistingId,productDTO);
		});
		
	}
	

}
