package dev.naman.productservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.server.ResponseStatusException;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.security.JwtObject;
import dev.naman.productservice.security.TokenValidator;
import dev.naman.productservice.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	//	//field injection
//	@Autowired
	private ProductService productService;
	private TokenValidator tokenValidator;

	//constructor injection & it is best recommended
	public ProductController(ProductService productService, TokenValidator tokenValidator) {  //@Qualifier("fakeStoreProductService")
		this.productService = productService;
		this.tokenValidator = tokenValidator;
	}//@Qualifier("fakeStoreProductService") bcos to avoide confuse which object should it take bcos we have 2 service
	//if @primary on serviceClass & @Qualifier both then Qual will take explicitly primary is only default if qual is not present

//	//setter injection
//  @Autowired
//	public void setProductService(ProductService productService){
//		this.productService = productService;
//	}

	@GetMapping()
	public List<GenericProductDto> getAllProducts() { //created DTO bcos we are interacting outside the (system fakestore) we can't use mode; objects bcos thpse are replicating to db tables
		return productService.getAllProducts();
	}

	//localhost:8080/products/{id}
	//localhost:8080/products/123
	@GetMapping("{id}")
	public GenericProductDto getProductById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken, @PathVariable("id") Long id) throws NotFoundException {
		//@PathVariable("id") is used so that Long id (in parameter) will match with the localhost:8080/products/{id}
		System.out.println(authToken);
//		Optional<JwtObject> authTokenObjOptional;
//		JwtObject authTokenObj = null;
//		if(authToken!=null){ //validate the token to send the call to userservice, we create security package related to security
//			authTokenObjOptional = tokenValidator.validateToken(authToken);
//			if(authTokenObjOptional.isEmpty()){//suppose if this api somehow has invalidate token but we still allow to run, we can do anything it's our api
//				//ignore
//			}
//			authTokenObj = authTokenObjOptional.get();
//		}
//		return productService.getProductById(id, authTokenObj.getUserId());

		// Call user-service via TokenValidator
		boolean isValid = tokenValidator.validateToken(authToken);

		if (!isValid) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token invalid or expired");
		}

		return productService.getProductById(id);
	}

	@PostMapping()
	public GenericProductDto createProduct(@RequestBody GenericProductDto product) { //@RequestBody whatever in the request body convert it into a GenericProductDto
		return productService.createProduct(product);
	}

	@DeleteMapping("{id}")
	public GenericProductDto deleteProductById(@PathVariable("id") Long id) {
		return productService.deleteProduct(id);
		//return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK); //make return type of ResponseEntity<GenericProductDto>
		//we can set Http code ourself
	}

	@PutMapping("{id}")
	public GenericProductDto updateProduct(@PathVariable("id") Long id, @RequestBody GenericProductDto productDto) {
		return productService.updateProduct(id, productDto);
	}
}