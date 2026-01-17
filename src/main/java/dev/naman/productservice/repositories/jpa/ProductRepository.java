package dev.naman.productservice.repositories.jpa;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.naman.productservice.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findById(Long id);
    boolean existsByCategory_id(Long category_id);
    Page<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
}