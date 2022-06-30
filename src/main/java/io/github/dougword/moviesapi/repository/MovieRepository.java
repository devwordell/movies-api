package io.github.dougword.moviesapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.dougword.moviesapi.domain.Movie;
import io.github.dougword.moviesapi.domain.WinnerProducer;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query(nativeQuery = true)
	List<WinnerProducer> getWinnerProducers();
	
}
