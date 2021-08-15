package com.dashboard.ipl.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.ipl.model.Match;
import com.dashboard.ipl.model.TeamData;
import com.dashboard.ipl.service.IplDashboardService;

/**
 * The Class TeamController.
 */
@RestController
@CrossOrigin
public class TeamController {

	/** The ipl dashboard service. */
	private IplDashboardService iplDashboardService;

	/**
	 * Instantiates a new team controller.
	 *
	 * @param iplDashboardService the ipl dashboard service
	 */
	public TeamController(IplDashboardService iplDashboardService) {
		super();
		this.iplDashboardService = iplDashboardService;
	}
	
	/**
	 * Gets the all teams.
	 *
	 * @return the all teams
	 */
	@GetMapping("/teams")
	public Iterable<TeamData> getAllTeams() {
		return iplDashboardService.getAllTeams();
	}

	/**
	 * Gets the team.
	 *
	 * @param teamName the team name
	 * @return the team
	 */
	@GetMapping("/teams/{teamName}")
	public TeamData getTeam(@PathVariable String teamName) {
		return iplDashboardService.getTeam(teamName);
	}
	
	/**
	 * Gets the matchs by year.
	 *
	 * @param teamName the team name
	 * @param year the year
	 * @return the matchs by year
	 */
	@GetMapping("/teams/{teamName}/matches")
	public List<Match> getMatchsByYear(@PathVariable String teamName, @RequestParam Integer year) {
		return iplDashboardService.getMatchesByYear(teamName, year);
	}

}
