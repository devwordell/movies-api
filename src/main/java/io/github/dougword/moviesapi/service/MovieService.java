package io.github.dougword.moviesapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dougword.moviesapi.domain.Movie;
import io.github.dougword.moviesapi.domain.Producer;
import io.github.dougword.moviesapi.domain.Studio;
import io.github.dougword.moviesapi.domain.csv.MovieCsv;
import io.github.dougword.moviesapi.repository.MovieRepository;
import io.github.dougword.moviesapi.repository.ProducerRepository;
import io.github.dougword.moviesapi.repository.StudioRepository;

@Service
public class MovieService {

	@Autowired
	private ProducerRepository producerRepository;

	@Autowired
	private StudioRepository studioRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Transactional
	public void saveMovies(List<MovieCsv> moviesCsv) {
		try {
			for (MovieCsv movieCsv : moviesCsv) {
				Movie movie = new Movie();
				movie.setYear(Integer.parseInt(movieCsv.getYear()));
				movie.setTitle(movieCsv.getTitle());
				movie.setWinner(movieCsv.getWinner());
				movie.setProducers(getMovieProducers(movieCsv.getProducers()));
				movie.setStudios(getMovieStudios(movieCsv.getStudios()));
				movieRepository.save(movie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Studio> getMovieStudios(List<String> studios) {
		List<Studio> studioList = new ArrayList<>();
		for (String studio : studios) {
			Studio studioEntity = getStudioEntity(studio);
			studioList.add(studioEntity);
		}
		return studioList;
	}

	private Studio getStudioEntity(String studio) {
		Optional<Studio> optionalStudio = studioRepository.findByName(studio);
		if (optionalStudio.isEmpty())
			return studioRepository.save(new Studio(studio));

		return optionalStudio.get();
	}

	private List<Producer> getMovieProducers(List<String> producers) {
		List<Producer> producerList = new ArrayList<>();
		for (String producer : producers) {
			Producer producerEntity = getProducerEntity(producer);
			producerList.add(producerEntity);
		}
		return producerList;
	}

	private Producer getProducerEntity(String producer) {
		Optional<Producer> optionalProducer = producerRepository.findByName(producer);
		if (optionalProducer.isEmpty())
			return producerRepository.save(new Producer(producer));

		return optionalProducer.get();
	}
	
}
