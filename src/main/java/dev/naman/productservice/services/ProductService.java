package dev.naman.productservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.security.JwtObject;

@Service
public interface ProductService {
	//every method in the controller will be same in the service
	GenericProductDto getProductById(Long id) throws NotFoundException;

	GenericProductDto createProduct(GenericProductDto product);

	List<GenericProductDto> getAllProducts();

	GenericProductDto deleteProduct(Long id);

	GenericProductDto updateProduct(Long id, GenericProductDto productDto);
}