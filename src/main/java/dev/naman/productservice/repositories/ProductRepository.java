package dev.naman.productservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.naman.productservice.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findById(Long id);
}