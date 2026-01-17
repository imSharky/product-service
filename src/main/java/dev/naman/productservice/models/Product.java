package dev.naman.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;

@Entity
public class Product extends BaseModel {
	private String title;
	private String description;
	private String image;
	@ManyToOne
	private Category category;
	private double price;

	public Product() { //no args cons
	}

	public Product(String title, String description, String image, Category category, double price) { //all args cons
		this.title = title;
		this.description = description;
		this.image = image;
		this.category = category;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}