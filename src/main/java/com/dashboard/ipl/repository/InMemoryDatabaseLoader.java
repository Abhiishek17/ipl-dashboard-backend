package com.dashboard.ipl.repository;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dashboard.ipl.model.TeamData;

/**
 * The Class InMemoryDatabaseLoader.
 */
@Component
public class InMemoryDatabaseLoader {

	/*
	 * EntityManager allows us to create JPQL query. It is like SQL query but on
	 * java model object.
	 */

	/** The entity manager. */
	@Autowired
	private EntityManager entityManager;
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(InMemoryDatabaseLoader.class);

	/** The Constant TEAM_ONE_QUERY. */
	private static final String TEAM_ONE_QUERY = "select m.team1, count(*) from Match m group by m.team1";
	
	/** The Constant TEAM_TWO_QUERY. */
	private static final String TEAM_TWO_QUERY = "select m.team2, count(*) from Match m group by m.team2";
	
	/** The Constant MATCH_WIN_QUERY. */
	private static final String MATCH_WIN_QUERY = "select m.winner, count(*) from Match m group by m.winner";

	/**
	 * Persist team data.
	 */
	@Transactional
	public void persistTeamData() {
		Map<String, TeamData> teamData = new HashMap<>();

		try {
			entityManager.createQuery(TEAM_ONE_QUERY, Object[].class)
					.getResultStream()
					.map(r -> new TeamData((String) r[0], (Long) r[1]))
					.forEach(team -> teamData.put(team.getTeamName(), team));
			

			/*TODO
			 * Fix a bug regarding what if the team has always played in a second position.
			 * In that case, it will not be available in the map.
			 * Delhi Daredevils to Delhi Captials.
			 */
			
			entityManager.createQuery(TEAM_TWO_QUERY, Object[].class)
					.getResultList()
					.forEach(result -> {
						TeamData team = teamData.get(result[0]);
						if (null != team)
							team.setTotalMatches(team.getTotalMatches() + (Long) result[1]);
					});
			
			entityManager.createQuery(MATCH_WIN_QUERY, Object[].class)
					.getResultList()
					.forEach(result -> {
						TeamData team = teamData.get(result[0]);
						if (null != team)
							team.setTotalWins((Long) result[1]);
					});

			/*
			 * Persisting to the database
			 */
			teamData.values().forEach(value -> entityManager.persist(value));
			LOG.info("Database loaded to memory");
		} catch (Exception exp) {
			LOG.error("Error in persisting data because of {}", exp.getMessage(), exp);
		}
		
	}

}
