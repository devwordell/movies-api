package io.github.dougword.moviesapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

@NamedNativeQuery(name = "Movie.getWinnerProducers",
				  query = "SELECT p.id, p.name, m.award_year " +
						  "FROM movie m " +
						  "JOIN movie_producer mp ON (mp.movie_id = m.id) " +
						  "JOIN producer p ON (p.id = mp.producer_id) " +
						  "WHERE m.winner = TRUE " +
						  "ORDER BY p.name, m.award_year",
				  resultSetMapping = "Mapping.WinnerProducer")
@SqlResultSetMapping(name = "Mapping.WinnerProducer",
   classes = @ConstructorResult(targetClass = WinnerProducer.class,
                                columns = {@ColumnResult(name = "id", type = Long.class),
                                           @ColumnResult(name = "name"),
                                           @ColumnResult(name = "award_year", type = Integer.class)}))

@Data
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "award_year")
	private int year;
	private String title;
	@Singular
	@ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "movie_studio", 
        joinColumns = { @JoinColumn(name = "movie_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "studio_id") }
    )
	private List<Studio> studios = new ArrayList<>();
	@Singular
	@ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "movie_producer", 
        joinColumns = { @JoinColumn(name = "movie_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "producer_id") }
    )
	private List<Producer> producers = new ArrayList<>();
	private boolean winner;

}
