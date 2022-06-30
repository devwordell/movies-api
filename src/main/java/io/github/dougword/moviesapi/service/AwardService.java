package io.github.dougword.moviesapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.dougword.moviesapi.domain.WinnerProducer;
import io.github.dougword.moviesapi.domain.dto.AwardWinnersDTO;
import io.github.dougword.moviesapi.domain.dto.WinnerInfoDTO;
import io.github.dougword.moviesapi.repository.MovieRepository;

@Service
public class AwardService {

	@Autowired
	private MovieRepository movieRepository;

	public AwardWinnersDTO getAwardWinners() {
		AwardWinnersDTO winners = new AwardWinnersDTO();
		
		List<WinnerProducer> winnerProducerList = movieRepository.getWinnerProducers();
		WinnerProducer lastRecord = new WinnerProducer();
		int minYears = Integer.MAX_VALUE, maxYears = Integer.MIN_VALUE;
		List<WinnerInfoDTO> minYearsItems = new ArrayList<>();
		List<WinnerInfoDTO> maxYearsItems = new ArrayList<>();
		for (WinnerProducer currentRecord : winnerProducerList) {
			if (!currentRecord.getProducerId().equals(lastRecord.getProducerId())) {
				lastRecord = currentRecord;
				continue;
			}

			int interval = currentRecord.getYear() - lastRecord.getYear();
			if (interval <= minYears) {
				if (interval < minYears) {
					minYears = interval;
					minYearsItems.clear();
				}
				WinnerInfoDTO winner = createWinnerInfo(lastRecord, currentRecord, interval);
				minYearsItems.add(winner);
			}
			if (interval >= maxYears) {
				if (interval > maxYears) {
					maxYears = interval;
					maxYearsItems.clear();
				}
				WinnerInfoDTO winner = createWinnerInfo(lastRecord, currentRecord, interval);
				maxYearsItems.add(winner);
			}
			lastRecord = currentRecord;
		}

		for (WinnerInfoDTO foundItem : minYearsItems)
			winners.getMin().add(foundItem);

		for (WinnerInfoDTO foundItem : maxYearsItems)
			winners.getMax().add(foundItem);
		
		return winners;
	}

	private WinnerInfoDTO createWinnerInfo(WinnerProducer lastRecord, WinnerProducer currentRecord, int interval) {
		WinnerInfoDTO winner =	WinnerInfoDTO.builder()
				.producer(currentRecord.getName())
				.interval(interval)
				.previousWin(lastRecord.getYear())
				.followingWin(currentRecord.getYear())
				.build();
		return winner;
	}
	
}
