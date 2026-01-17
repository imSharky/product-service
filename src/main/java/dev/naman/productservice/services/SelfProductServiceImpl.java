package dev.naman.productservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.jpa.CategoryRepository;
import dev.naman.productservice.repositories.jpa.ProductRepository;


@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
    //private ProductElasticSearchRepository productElasticSearchRepository;

	public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	private GenericProductDto convertToDto(Product product) {
		GenericProductDto dto = new GenericProductDto();
		dto.setId(product.getId());
		dto.setTitle(product.getTitle());
		dto.setDescription(product.getDescription());
		dto.setImage(product.getImage());
		dto.setCategory(product.getCategory() != null ? product.getCategory().getName() : null);
		dto.setPrice(product.getPrice());
		return dto;
	}

	@Override
	public GenericProductDto getProductById(Long id) throws NotFoundException {
		//suppose a product is private only who created the product is allowed to see the product so we pass userIdTryingToAccess

		Optional<Product> productOptional = productRepository.findById(id);
		if(productOptional.isEmpty()){
			throw new NotFoundException("Product with id " + id + " not found");
		}
		Product product = productOptional.get();
		//i removed parameter , Long userIdTryingToAccess
		//if(product.getStatus().equals(PRIVATE)){
			//if(userIdTryingToAccess.equals(product.getCreatedId())){
				//return product;
			//}
			//return null;
		//}
		//return product; //if the product is not private then no need to authorization

		return convertToDto(product);
	}

	@Override
	public GenericProductDto createProduct(GenericProductDto productDto) {
		Category category = categoryRepository.findByName(productDto.getCategory())
											  .orElseGet(() -> {
												  Category newCategory = new Category();
												  newCategory.setName(productDto.getCategory());
												  return categoryRepository.save(newCategory);
											  });
		//dto to entity
		Product product1 = new Product(productDto.getTitle(), productDto.getDescription(), productDto.getImage(), category, productDto.getPrice());
		Product savedProduct = productRepository.save(product1);

		return convertToDto(savedProduct);

	}

	@Override
	public List<GenericProductDto> getAllProducts() {
		List<Product> products = productRepository.findAll();

		return products.stream().map(product -> {
			return convertToDto(product);
		}).toList();
	}

	@Override
	public GenericProductDto deleteProduct(Long id) {
//		Optional<Product> productOptional = productRepository.findById(id);
		Product product = productRepository.findById(id)
										   .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
        Long categoryId = product.getCategory().getId();
        productRepository.deleteById(id);
        //if there is no product of such category then delete that category
        if(!productRepository.existsByCategory_id(categoryId)) {
            categoryRepository.deleteById(categoryId);
        }
		//System.out.println("deleted successfully");

		return convertToDto(product);
	}

	@Override
	public GenericProductDto updateProduct(Long id, GenericProductDto productDto) {
		Product product = productRepository.findById(id)
										   .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
		//update fields
		product.setDescription(productDto.getDescription());
		product.setTitle(productDto.getTitle());
		product.setPrice(productDto.getPrice());
		product.setImage(productDto.getImage());
		//handle category
		Category category = categoryRepository.findByName(productDto.getCategory())
											  .orElseGet(() -> {
												  Category newCategory = new Category();
												  newCategory.setName(productDto.getCategory());
												  return categoryRepository.save(newCategory);
											  });
		product.setCategory(category);
		//save updated product
		Product savedProduct = productRepository.save(product);

		//map to dto

		return convertToDto(savedProduct);
	}
}