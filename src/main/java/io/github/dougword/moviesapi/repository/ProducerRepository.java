package io.github.dougword.moviesapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dougword.moviesapi.domain.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
	
	Optional<Producer> findByName(String name);
	
}
