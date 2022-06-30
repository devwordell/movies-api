package io.github.dougword.moviesapi.service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;

import io.github.dougword.moviesapi.domain.csv.MovieCsv;

@Component
public class DatabaseCsvLoader implements CommandLineRunner {
	
	@Value("${application.movielist}")
	private String csvFileName;

	@Autowired
	private MovieService movieService;
	
	@Override
	public void run(String... args) throws Exception {
		try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFileName));
            CSVParser csvParser = new CSVParserBuilder()
                    .withSeparator(';')
                    .withIgnoreQuotations(true)
                    .build();
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withCSVParser(csvParser)
                    .build();
            List<MovieCsv> moviesCsv = new CsvToBeanBuilder<MovieCsv>(csvReader)
            		.withType(MovieCsv.class)
            		.build()
            		.parse();
            movieService.saveMovies(moviesCsv);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}
