package com.dashboard.ipl.processor;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import com.dashboard.ipl.model.Match;
import com.dashboard.ipl.model.MatchInput;

/**
 * The Class MatchDataProcessor.
 */
public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

	/**
	 * Process.
	 *
	 * @param matchInput the match input
	 * @return the match
	 * @throws Exception the exception
	 */
	@Override
	public Match process(MatchInput matchInput) throws Exception {
		String firstInningTeam;
		String secondInningTeam;

		if ("bat".equalsIgnoreCase(matchInput.getTossDecision())) {
			firstInningTeam = matchInput.getTossWinner();
			secondInningTeam = matchInput.getTeam1().equalsIgnoreCase(matchInput.getTossWinner())
					? matchInput.getTeam2()
					: matchInput.getTeam1();

		} else {
			secondInningTeam = matchInput.getTossWinner();
			firstInningTeam = matchInput.getTeam1().equalsIgnoreCase(matchInput.getTossWinner()) ? matchInput.getTeam2()
					: matchInput.getTeam1();
		}

		Match match = new Match();
		match.setId(Long.valueOf(matchInput.getId()));
		match.setCity(matchInput.getCity());
		match.setDate(LocalDate.parse(matchInput.getDate()));
		match.setPlayerOfMatch(matchInput.getPlayerOfMatch());
		match.setVenue(matchInput.getVenue());
		match.setTeam1(matchInput.getTeam1());
		match.setTeam2(matchInput.getTeam2());
		match.setTossWinner(matchInput.getTossWinner());
		match.setTossDecision(matchInput.getTossDecision());
		match.setWinner(matchInput.getWinner());
		match.setResult(matchInput.getResult());
		match.setResultMargin(matchInput.getResultMargin());
		match.setUmpire1(matchInput.getUmpire1());
		match.setUmpire2(matchInput.getUmpire2());
		match.setTeam1(firstInningTeam);
		match.setTeam2(secondInningTeam);

		return match;
	}

}
