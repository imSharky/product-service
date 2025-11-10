package dev.naman.productservice.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Category extends BaseModel{
	private String name;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
	private List<Product> products;

//	public Category() {
//	}
//
//	public Category(String name, List<Product> products) {
//		this.name = name;
//		this.products = products;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}