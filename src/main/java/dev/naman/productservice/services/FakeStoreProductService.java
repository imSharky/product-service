package dev.naman.productservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.security.JwtObject;
import dev.naman.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.naman.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;

//@Primary //no need in controller @Qualifier("fakeStoreProductService") bco primary makes default
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
	private FakeStoreProductServiceClient fakeStoreProductServiceClient;

	private GenericProductDto convertFakeStoreProductntoGenericProduct(FakeStoreProductDto fakeStoreProductDto) {
		GenericProductDto product = new GenericProductDto();

		product.setId(fakeStoreProductDto.getId());
		product.setImage(fakeStoreProductDto.getImage());
		product.setDescription(fakeStoreProductDto.getDescription());
		product.setTitle(fakeStoreProductDto.getTitle());
		product.setPrice(fakeStoreProductDto.getPrice());
		product.setCategory(fakeStoreProductDto.getCategory());

		return product;
	}

	public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
		this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
	}

	@Override
	public GenericProductDto getProductById(Long id) throws NotFoundException {
		return convertFakeStoreProductntoGenericProduct(fakeStoreProductServiceClient.getProductById(id));
	}

	@Override
	public GenericProductDto createProduct(GenericProductDto product) {
		return convertFakeStoreProductntoGenericProduct(fakeStoreProductServiceClient.createProduct(product));
	}

	@Override
	public List<GenericProductDto> getAllProducts() {
		List<GenericProductDto> genericProductDtos = new ArrayList<>();

		for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductServiceClient.getAllProducts()) {
			genericProductDtos.add(convertFakeStoreProductntoGenericProduct(fakeStoreProductDto));
		}
		return genericProductDtos;
	}

	@Override
	public GenericProductDto deleteProduct(Long id) {
		return convertFakeStoreProductntoGenericProduct(fakeStoreProductServiceClient.deleteProduct(id));
	}

	@Override
	public GenericProductDto updateProduct(Long id, GenericProductDto product) {
		return convertFakeStoreProductntoGenericProduct(fakeStoreProductServiceClient.updateProduct(id, product));
	}
}
// all the api calls things will be handles by clients
//clien is wrapper over a particular api like here fakeStoreProductServiceClient class

//whenever we are talking to a third party api we first implement it's client then use particular client via particular services