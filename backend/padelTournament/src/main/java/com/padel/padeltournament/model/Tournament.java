package com.padel.padeltournament.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import jakarta.persistence.*;

@Entity
public class Tournament {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private boolean started = false;
  private boolean finished = false;
  private int courts;
  private String createdAt;
  @Convert(converter = TeamListConverter.class)
  @Column(columnDefinition = "CLOB")
  private List<Team> teams;

  @Convert(converter = MatchListConverter.class)
  @Column(columnDefinition = "CLOB")
  private List<Match> matches;

  @Convert(converter = MatchListConverter.class)
  @Column(columnDefinition = "CLOB")
  private List<Match> playedMatches;

  @Convert(converter = MatchListConverter.class)
  @Column(columnDefinition = "CLOB")
  private List<Match> currentMatches = new ArrayList<>();

  // --- Constructor ---

  public Tournament() {
  }

  public Tournament(String name) {
    this.name = name;
    this.started = true;
    this.finished = false;
    this.createdAt = LocalDateTime.now().toString();
    this.teams = new ArrayList<>();
    this.matches = new ArrayList<>();
    this.playedMatches = new ArrayList<>();
  }

  public void addTeam(Team team) {
    this.teams.add(team);
  }

  public void removeTeam(int index) {
    this.teams.remove(index);
  }

  /**
   * Starts the tournament by generating all possible matches between teams.
   * 
   * For each unique pair of teams, a new Match is created and added to the list
   * of matches.
   *
   * Sets the tournament's "started" flag to true.
   */
  public void start() {
    this.started = true;

    for (int i = 0; i < teams.size(); i++) {
      for (int j = i + 1; j < teams.size(); j++) {
        matches.add(new Match(teams.get(i), teams.get(j)));
      }
    }
  }

  /**
   * Retrieves the next matches that should be played, based on the available
   * number of courts.
   *
   * If there are already active matches (currentMatches), those are returned
   * directly.
   * Otherwise:
   * - Selects new matches where no team is scheduled to play twice.
   * - Prioritizes matches where teams have played the fewest games.
   * - Selects up to the number of available courts.
   *
   * Updates "currentMatches" with the selected matches and removes them from the
   * pool of unplayed matches.
   *
   * @param courts the number of available courts
   * @return a list of matches to be played next
   */
  public List<Match> getNextMatches() {
    if (currentMatches != null && !currentMatches.isEmpty()) {
      return currentMatches;
    }
    List<Match> nextMatches = new ArrayList<>();
    Set<Team> teamsInPlay = new HashSet<>();

    // Sort matches based on least played
    List<Match> sorted = new ArrayList<>(matches);
    sorted.sort(Comparator.comparingInt(
        m -> m.getTeam1().getPlayedMatches() + m.getTeam2().getPlayedMatches()));

    for (Iterator<Match> iterator = sorted.iterator(); iterator.hasNext();) {
      if (nextMatches.size() >= this.courts)
        break;

      Match match = iterator.next();
      Team t1 = match.getTeam1();
      Team t2 = match.getTeam2();

      if (teamsInPlay.contains(t1) || teamsInPlay.contains(t2))
        continue;

      nextMatches.add(match);
      teamsInPlay.add(t1);
      teamsInPlay.add(t2);
      matches.remove(match);
    }
    currentMatches = nextMatches;
    return nextMatches;
  }

  /**
   * update the matches, takes a list of updated matches add it to played matches
   * and update the scoreboard
   */
  public void updateMatches(List<Match> matches) {
    for (Match match : matches) {
      Team winner = match.getWinner();
      Team loser = match.getLoser();
      int score = match.getScore();
      if (winner == null) {
        System.out.println("fanns ingen vinnare så lika");
        this.playedMatches.add(match);
        continue;
      }

      Team actualWinner = findTeamByName(winner.getName());
      Team actualLoser = findTeamByName(loser.getName());

      actualWinner.addScore(score);
      actualWinner.addWonMatch();

      actualLoser.subtractScore(score);
      this.playedMatches.add(match);
      match.setPlayed(true);

      actualWinner.addPlayedMatch();
      actualLoser.addPlayedMatch();
    }
    currentMatches.clear();
  }

  private Team findTeamByName(String name) {
    for (Team t : this.teams) {
      if (t.getName().equals(name)) {
        return t;
      }
    }
    return null;
  }

  // --- Getters & Setters ---
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    System.out.println("namn: " + name);
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

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public List<Team> getTeams() {
    return teams;
  }

  public void setTeams(List<Team> teams) {
    this.teams = teams;
  }

  public int getCourts() {
    return courts;
  }

  public void setCourts(int courts) {
    this.courts = courts;
  }

  public List<Team> getStandings() {
    List<Team> sorted = new ArrayList<>(teams);
    sorted.sort(
        Comparator
            .comparingInt(Team::getWonMatches).reversed()
            .thenComparing(Comparator.comparingInt(Team::getScore).reversed()));
    for (Team team : sorted) {
      System.out
          .println("sent order: " + team.getName() + " won: " + team.getWonMatches() + " score: " + team.getScore());
    }
    return sorted;
  }

}
