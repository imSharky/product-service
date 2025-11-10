package dev.naman.productservice.thirdpartyclients.productservice.fakestore;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;

//wrapper over FakeStore API

@Service
// annotation is required bcos spring will create objects for the class annotated these are stereotype annotated
public class FakeStoreProductServiceClient {
	private RestTemplateBuilder restTemplateBuilder; //it is used to call 3rd party API get the data from api & do ur work

	@Value("${fakestore.api.url}")
	private String fakeStoreApiUrl;

	@Value("${fakestore.api.paths.product}")
	private String fakeStoreProductsApiPath;

	private String specificProductRequestUrl;
	private String productRequestBaseUrl;

	public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakestore.api.url}")String fakeStoreApiUrl, @Value("${fakestore.api.paths.product}")String fakeStoreProductsApiPath) {
		this.restTemplateBuilder = restTemplateBuilder;
		this.productRequestBaseUrl = fakeStoreApiUrl + fakeStoreProductsApiPath;
		this.specificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
	}

	public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
		RestTemplate restTemplate = restTemplateBuilder.build();
		ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
		//url is request class is response id is variable
		FakeStoreProductDto fakeStoreProductDto = response.getBody();

		if (fakeStoreProductDto == null) {
			throw new NotFoundException("Product with id: " + id + " not found");
		}

		return fakeStoreProductDto;
	}

	public FakeStoreProductDto createProduct(GenericProductDto product) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestBaseUrl, product, FakeStoreProductDto.class);
		return response.getBody();
	}

	public List<FakeStoreProductDto> getAllProducts() {
		RestTemplate restTemplate = restTemplateBuilder.build();
		ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);

//		List<GenericProductDto> answer = new ArrayList<>();
//
//		for (FakeStoreProductDto fakeStoreProductDto : Arrays.stream(response.getBody())
//															 .toList()) {
//			answer.add(convertFakeStoreProductntoGenericProduct(fakeStoreProductDto));
//		}
//		return answer;
		return Arrays.stream(response.getBody())
					 .toList();
	}

	public FakeStoreProductDto deleteProduct(Long id) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		//restTemplate.delete(specificProductRequestUrl); //if on delete not return anything but we are returning so use exchange or executor

		RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
		ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
		ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

		//FakeStoreProductDto fakeStoreProductDto = response.getBody();

		return response.getBody();
	}

	public FakeStoreProductDto updateProduct(Long id, GenericProductDto productDto) {
		RestTemplate restTemplate = restTemplateBuilder.build();

		// Convert incoming GenericProductDto to FakeStoreProductDto if needed
//		FakeStoreProductDto fakeStoreRequestDto = new FakeStoreProductDto();
//		fakeStoreRequestDto.setTitle(productDto.getTitle());
//		fakeStoreRequestDto.setPrice(productDto.getPrice());
//		fakeStoreRequestDto.setCategory(productDto.getCategory());
//		fakeStoreRequestDto.setDescription(productDto.getDescription());
//		fakeStoreRequestDto.setImage(productDto.getImage());
		// Create an HTTP entity with the request body
		HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(productDto);

		// Perform PUT request
		ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(specificProductRequestUrl,            // URL with {id}
				HttpMethod.PUT, requestEntity, FakeStoreProductDto.class, id                                     // Path variable for {id}
		);
		//FakeStoreProductDto fakeStoreProductDto = response.getBody();

		//return convertFakeStoreProductntoGenericProduct(fakeStoreProductDto);
		return response.getBody();
	}
}