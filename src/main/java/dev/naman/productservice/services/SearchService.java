package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.dtos.SortParameter;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.jpa.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private ProductRepository productRepository;
    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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

    public Page<GenericProductDto> searchProducts(String query, int pageNumber, int pageSize, List<SortParameter> sortByParameters){

        //Sort sort = Sort.by("title").descending().and(Sort.by("price").descending()); //hardcoded

        Sort sort;
        if(sortByParameters.get(0).getSortType().equals("ASC")){
            sort = Sort.by(sortByParameters.get(0).getParameterName()); //by default ascending
        } else{
            sort = Sort.by(sortByParameters.get(0).getParameterName()).descending();
        }
        for(int i=1;i< sortByParameters.size();i++){
            if(sortByParameters.get(i).getSortType().equals("ASC")){
                sort = sort.and(Sort.by(sortByParameters.get(i).getParameterName()));
            } else{
                sort = sort.and(Sort.by(sortByParameters.get(i).getParameterName()).descending());
            }
            //if all parameter clashes then sort by id by default
        }

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<Product> productPage = productRepository.findAllByTitleContainingIgnoreCase(query, pageable);
        List<Product> products = productPage.get().collect(Collectors.toList());

        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for(Product product : products){
            genericProductDtos.add(convertToDto(product));
        }
        Page<GenericProductDto> genericProductPage =  new PageImpl<>(genericProductDtos, productPage.getPageable(), productPage.getTotalElements());
        return genericProductPage;
    }
}
