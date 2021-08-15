package com.dashboard.ipl.repository;

import org.springframework.data.repository.CrudRepository;

import com.dashboard.ipl.model.TeamData;

/**
 * The Interface TeamRepository.
 */
public interface TeamRepository extends CrudRepository<TeamData, Long> {
	
	/**
	 * Find by team name.
	 *
	 * @param teamName the team name
	 * @return the team data
	 */
	TeamData findByTeamName(String teamName);
}
