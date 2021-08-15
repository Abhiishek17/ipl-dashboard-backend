package com.dashboard.ipl.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.ipl.model.Match;
import com.dashboard.ipl.model.TeamData;
import com.dashboard.ipl.repository.InMemoryDatabaseLoader;
import com.dashboard.ipl.repository.MatchRepository;
import com.dashboard.ipl.repository.TeamRepository;

/**
 * The Class IplDashboardService.
 */
@Service
public class IplDashboardService {

	/** The in memory database loader. */
	@Autowired
	private InMemoryDatabaseLoader inMemoryDatabaseLoader;

	/** The team repository. */
	private TeamRepository teamRepository;

	/** The match repository. */
	private MatchRepository matchRepository;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(IplDashboardService.class);

	/**
	 * Instantiates a new ipl dashboard service.
	 *
	 * @param teamRepository  the team repository
	 * @param matchRepository the match repository
	 */
	public IplDashboardService(TeamRepository teamRepository, MatchRepository matchRepository) {
		super();
		this.teamRepository = teamRepository;
		this.matchRepository = matchRepository;
	}

	/**
	 * Creates the team data.
	 */
	public void createTeamData() {
		inMemoryDatabaseLoader.persistTeamData();
	}

	/**
	 * Gets the all teams.
	 *
	 * @return the all teams
	 */
	public Iterable<TeamData> getAllTeams() {
		return this.teamRepository.findAll();
	}

	/**
	 * Gets the team.
	 *
	 * @param teamName the team name
	 * @return the team
	 */
	public TeamData getTeam(String teamName) {
		try {
			TeamData team = this.teamRepository.findByTeamName(teamName);
			team.setMatchList(this.matchRepository.findLatestMatchByDate(teamName, 4));
			return team;
		} catch (Exception e) {
			LOG.error("Error in fetch team details because of {}", e.getMessage(), e);
			return null;
		}
	}

	/**
	 * Gets the matches by year.
	 *
	 * @param teamName the team name
	 * @param year     the year
	 * @return the matches by year
	 */
	public List<Match> getMatchesByYear(String teamName, Integer year) {
		LocalDate startDate = LocalDate.of(year, 1, 1);
		LocalDate endDate = LocalDate.of((year + 1), 1, 1);
		return this.matchRepository.getMatchesByYear(teamName, startDate, endDate);
	}
}
