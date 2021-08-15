package com.dashboard.ipl.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * The Class TeamData.
 */
@Entity
public class TeamData {
	
	/**
	 * Instantiates a new team data.
	 */
	public TeamData() {
		super();
	}

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long totalMatches;
	private Long totalWins;
	private String teamName;

	@Transient
	private List<Match> matchList;

	/**
	 * Instantiates a new team data.
	 *
	 * @param teamName     the team name
	 * @param totalMatches the total matches
	 */
	public TeamData(String teamName, Long totalMatches) {
		super();
		this.teamName = teamName;
		this.totalMatches = totalMatches;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the total matches.
	 *
	 * @return the totalMatches
	 */
	public Long getTotalMatches() {
		return totalMatches;
	}

	/**
	 * Sets the total matches.
	 *
	 * @param totalMatches the totalMatches to set
	 */
	public void setTotalMatches(Long totalMatches) {
		this.totalMatches = totalMatches;
	}

	/**
	 * Gets the total wins.
	 *
	 * @return the totalWins
	 */
	public Long getTotalWins() {
		return totalWins;
	}

	/**
	 * Sets the total wins.
	 *
	 * @param totalWins the totalWins to set
	 */
	public void setTotalWins(Long totalWins) {
		this.totalWins = totalWins;
	}

	/**
	 * Gets the team name.
	 *
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * Sets the team name.
	 *
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * Gets the match list.
	 *
	 * @return the matchList
	 */
	public List<Match> getMatchList() {
		return matchList;
	}

	/**
	 * Sets the match list.
	 *
	 * @param matchList the matchList to set
	 */
	public void setMatchList(List<Match> matchList) {
		this.matchList = matchList;
	}

	/**
	 * To string.
	 *
	 * @return the toString
	 */
	@Override
	public String toString() {
		return "TeamData [id=" + id + ", totalMatches=" + totalMatches + ", totalWins=" + totalWins + ", teamName="
				+ teamName + "]";
	}

}
