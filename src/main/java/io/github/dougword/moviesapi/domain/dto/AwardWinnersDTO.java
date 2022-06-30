package io.github.dougword.moviesapi.domain.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AwardWinnersDTO {
	
	private List<WinnerInfoDTO> min = new ArrayList<>();
	private List<WinnerInfoDTO> max = new ArrayList<>();

}
