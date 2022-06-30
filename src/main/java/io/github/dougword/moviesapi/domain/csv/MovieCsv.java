package io.github.dougword.moviesapi.domain.csv;

import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import lombok.Data;

@Data
public class MovieCsv {
	
	/* Regex used to split list of words. Considers ', and ', ',' and 'and'
	 * There must be an space before and after the word 'and', except when preceded by , (comma) */
	private static final String COMMA_AND_SEPARATOR = "\\s*,\\s*and\\s+|\\s*,\\s*|\\s+and\\s+";
	
	@CsvBindByName
	private String year;
	
	@CsvBindByName
	private String title;
	
	@CsvBindAndSplitByName(elementType = String.class, splitOn = COMMA_AND_SEPARATOR)
	private List<String> studios;

	@CsvBindAndSplitByName(elementType = String.class, splitOn = COMMA_AND_SEPARATOR)
	private List<String> producers;
	
	@CsvCustomBindByName(converter = YesNoToBooleanCsvConverter.class)
	private Boolean winner;

	private String commaSeparatedList(List<String> list) {
		return list.stream().map(item -> "'" + item + "'").collect(Collectors.joining(", "));
	}
	
	@Override
	public String toString() {
		return "MovieCsv [year=" + year + ", title=" + title + 
				", studios=" + commaSeparatedList(studios) + 
				", producers=" + commaSeparatedList(producers)
				+ ", winner=" + winner + "]";
	}

}
