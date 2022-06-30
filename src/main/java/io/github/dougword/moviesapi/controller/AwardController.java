package io.github.dougword.moviesapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dougword.moviesapi.domain.dto.AwardWinnersDTO;
import io.github.dougword.moviesapi.service.AwardService;

@RestController
@RequestMapping("/")
public class AwardController {
	
	@Autowired
	private AwardService awardService;
	
	@GetMapping
	public AwardWinnersDTO getWinners() {
		return awardService.getAwardWinners();
	}

}
