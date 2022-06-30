package io.github.dougword.moviesapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dougword.moviesapi.domain.Studio;

public interface StudioRepository extends JpaRepository<Studio, Long> {

	Optional<Studio> findByName(String name);
	
}
