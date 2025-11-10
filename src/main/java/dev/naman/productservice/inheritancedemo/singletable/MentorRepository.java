package dev.naman.productservice.inheritancedemo.singletable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
	@Override
	<S extends Mentor> S save(S entity);
}