package io.github.dougword.moviesapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WinnerInfoDTO {

	private String producer;
	private int interval;
	private int previousWin;
	private int followingWin;
	
}
