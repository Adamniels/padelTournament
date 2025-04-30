package com.padel.padeltournament.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TournamentTest {

  private Tournament tournament;
  private Team teamA;
  private Team teamB;

  @BeforeEach
  public void setup() {
    tournament = new Tournament("Test Tournament");
    teamA = new Team(1L, "Team A");
    teamB = new Team(2L, "Team B");
    tournament.setCourts(2);
    tournament.start();
  }

  @Test
  public void testAddAndRemoveTeam() {
    tournament.addTeam(teamA);
    tournament.addTeam(teamB);
    assertEquals(2, tournament.getTeams().size());

    tournament.removeTeam(0);
    assertEquals(1, tournament.getTeams().size());
    assertEquals("Team B", tournament.getTeams().get(0).getName());
  }

  @Test
  public void testStartGeneratesMatches() {
    tournament.addTeam(teamA);
    tournament.addTeam(teamB);
    tournament.start();

    List<Match> nextMatches = tournament.getNextMatches();
    assertEquals(1, nextMatches.size());
    Match match = nextMatches.get(0);
    assertEquals("Team A", match.getTeam1().getName());
    assertEquals("Team B", match.getTeam2().getName());
  }

  @Test
  public void testMatchUpdateAndScoreboard() {
    tournament.addTeam(teamA);
    tournament.addTeam(teamB);
    tournament.start();

    Match match = new Match(teamA, teamB, 6, 3);
    tournament.updateMatches(List.of(match));

    List<Team> standings = tournament.getStandings();
    assertEquals("Team A", standings.get(0).getName());
    assertEquals(3, standings.get(0).getScore());
    assertEquals(1, standings.get(0).getWonMatches());
    assertEquals(-3, standings.get(1).getScore());
  }

  @Test
  public void testDrawMatch() {
    tournament.addTeam(teamA);
    tournament.addTeam(teamB);
    tournament.start();

    Match draw = new Match(teamA, teamB, 5, 5);
    tournament.updateMatches(List.of(draw));

    assertEquals(0, tournament.getStandings().get(0).getScore()); // båda har 0
    assertEquals(0, tournament.getStandings().get(1).getScore());
  }
}
