package dev.naman.productservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.security.JwtObject;
import dev.naman.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.naman.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;

//@Primary //no need in controller @Qualifier("fakeStoreProductService") bco primary makes default
@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {
	private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    private RedisTemplate<String, Object> redisTemplate;
    //String : datatype of key
    //Object : datatype of value

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient, RedisTemplate<String, Object> redisTemplate) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
        this.redisTemplate = redisTemplate;
    }

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


	@Override
	public GenericProductDto getProductById(Long id) throws NotFoundException {
        //check if this product id already exist in my cache
        // if Yes : return from cache
        // else : make an api call to fakestore, save details in cache return
        GenericProductDto genericProductDto = (GenericProductDto) redisTemplate.opsForHash().get("PRODUCTS", id);
        if(genericProductDto != null) { //exist in cache
            return genericProductDto;
        }
        GenericProductDto genericProductDto1 = convertFakeStoreProductntoGenericProduct(fakeStoreProductServiceClient.getProductById(id)); //make an api call
        redisTemplate.opsForHash().put("PRODUCTS", id, genericProductDto1); // save to cache
		return genericProductDto1;
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