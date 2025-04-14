package com.padel.padeltournament.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.padel.padeltournament.model.Match;

public class Tournament {
  private Integer id; // kan vara null tills man sparar
  private String name;
  private boolean started = false;
  private boolean finished = false;
  private LocalDateTime createdAt;
  private List<Team> teams;
  private Map<Team, Integer> matchCount;
  private List<Team> standings;
  private List<Match> matches;
  private List<Match> playedMatches;

  // --- Constructor ---

  public Tournament() {
  }

  public Tournament(String name) {
    this.name = name;
    this.started = true;
    this.finished = false;
    this.createdAt = LocalDateTime.now();
    this.teams = new ArrayList<>();
    this.matches = new ArrayList<>();
    this.playedMatches = new ArrayList<>();
    this.matchCount = new HashMap<>();

  }

  public void addTeam(Team team) {
    this.teams.add(team);
  }

  public void removeTeam(int index) {
    this.teams.remove(index);
  }

  public void start() {
    this.started = true;
    this.standings = new ArrayList<>(this.teams);

    for (int i = 0; i < teams.size(); i++) {
      for (int j = i + 1; j < teams.size(); j++) {
        matches.add(new Match(teams.get(i), teams.get(j)));
      }
    }

    // intitiate matchCount
    for (Team team : teams) {
      matchCount.put(team, 0);
    }
  }

  /**
   * Gets the next matches to be played based on the number of courts.
   *
   * return a list och all the matches, if there isn't enough matches then it will
   * return less
   */
  public List<Match> getNextMatches(int courts) {
    List<Match> nextMatches = new ArrayList<>();
    Set<Team> teamsInPlay = new HashSet<>();

    // Sortera matcherna så att de med minst spelade matcher först prioriteras
    List<Match> sorted = new ArrayList<>(matches);
    sorted.sort(Comparator.comparingInt(
        m -> matchCount.get(m.getTeam1()) + matchCount.get(m.getTeam2())));

    for (Match match : sorted) {
      if (nextMatches.size() >= courts)
        break;

      Team t1 = match.getTeam1();
      Team t2 = match.getTeam2();

      if (teamsInPlay.contains(t1) || teamsInPlay.contains(t2))
        continue;

      nextMatches.add(match);
      teamsInPlay.add(t1);
      teamsInPlay.add(t2);

      matches.remove(match);

      matchCount.put(t1, matchCount.get(t1) + 1);
      matchCount.put(t2, matchCount.get(t2) + 1);
    }

    return nextMatches;
  }

  /**
   * update the matches, takes a list of updated matches add it to played matches
   * and update the scoreboard
   */
  public void updateMatches(List<Match> matches) {
    // TODO:
    throw new Error("TODO: Not implemented");

    // update the the teams score
    // add the match to played matches
  }

  // --- Getters & Setters ---
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isStarted() {
    return started;
  }

  public void setStarted(boolean started) {
    this.started = started;
  }

  public boolean isFinished() {
    return finished;
  }

  public void setFinished(boolean finished) {
    this.finished = finished;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public List<Team> getTeams() {
    return teams;
  }

  public void setTeams(List<Team> teams) {
    this.teams = teams;
  }

  public List<Team> getStandings() {
    return standings;
  }

}
