package dev.naman.productservice.repositories.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.naman.productservice.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByName(String name);
}