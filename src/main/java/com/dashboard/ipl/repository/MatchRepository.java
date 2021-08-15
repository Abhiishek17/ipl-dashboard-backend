package com.dashboard.ipl.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dashboard.ipl.model.Match;

/**
 * The Interface MatchRepository.
 */
public interface MatchRepository extends CrudRepository<Match, Long> {
	
	/**
	 * Gets the by team 1 or team 2 order by date desc.
	 *
	 * @param teamName1 the team name 1
	 * @param teamName2 the team name 2
	 * @param pageable the pageable
	 * @return the by team 1 or team 2 order by date desc
	 */
	List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

	/**
	 * Gets the by team 1 and date between or team 2 and date between order by date desc.
	 *
	 * @param teamName1 the team name 1
	 * @param team1StartDate the team 1 start date
	 * @param team1EndDate the team 1 end date
	 * @param teamName2 the team name 2
	 * @param team2StartDate the team 2 start date
	 * @param team2EndDate the team 2 end date
	 * @return the by team 1 and date between or team 2 and date between order by date desc
	 */
	List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(String teamName1, LocalDate team1StartDate,
			LocalDate team1EndDate, String teamName2, LocalDate team2StartDate, LocalDate team2EndDate);

	/**
	 * Gets the matches by year.
	 *
	 * @param teamName the team name
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the matches by year
	 */
	/*
	 * JPQL way of doing above method
	 */
	@Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :startDate and :endDate")
	List<Match> getMatchesByYear(@Param("teamName") String teamName, LocalDate startDate, LocalDate endDate);

	/**
	 * Find latest match by date.
	 *
	 * @param teamName the team name
	 * @param page the page
	 * @return the list
	 */
	default List<Match> findLatestMatchByDate(String teamName, int page) {
		return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, page));
	}
}