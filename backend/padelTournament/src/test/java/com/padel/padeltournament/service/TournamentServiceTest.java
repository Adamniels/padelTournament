package com.padel.padeltournament.service;

import com.padel.padeltournament.model.Team;
import com.padel.padeltournament.model.Tournament;
import com.padel.padeltournament.model.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TournamentServiceTest {
  private TournamentService service;

  @BeforeEach
  public void setup() {
    this.service = new TournamentService();
    this.service.setActiveTournament(new Tournament("test"));
  }

  @Test
  public void TestAddAndRemove() {
    Team team = new Team(1L, "team1");
    service.addTeamTournament(team);

    assertEquals(1, service.getTeamsTournament().size());
    service.removeTeamTournament(0);
    assertEquals(0, service.getTeamsTournament().size());
  }

  @Test
  public void testStartTournamentAndGetNextMatches_2Teams() {
    service.addTeamTournament(new Team(1L, "Team A"));
    service.addTeamTournament(new Team(2L, "Team B"));
    service.startTournament("Padel Cup", 2);

    List<Match> matches = service.getNextMatches();
    assertEquals(1, matches.size());
  }
  // TODO: Testa mer
}
