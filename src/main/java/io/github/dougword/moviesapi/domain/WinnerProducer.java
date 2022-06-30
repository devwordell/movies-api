package io.github.dougword.moviesapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class WinnerProducer {

	private Long producerId;
	private String name;
	private int year;
	
}
