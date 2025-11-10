package dev.naman.productservice.inheritancedemo.singletable;

import jakarta.persistence.Entity;

@Entity(name = "st_mentor")
public class Mentor extends User {
	private double averageRating;

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
}