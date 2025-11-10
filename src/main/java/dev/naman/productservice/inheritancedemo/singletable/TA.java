package dev.naman.productservice.inheritancedemo.singletable;

import jakarta.persistence.Entity;

@Entity(name = "st_ta")
public class TA extends User {
	private double averageRating;

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
}