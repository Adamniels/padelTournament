package com.padel.padeltournament.controller;

import com.padel.padeltournament.model.Tournament;
import com.padel.padeltournament.model.Team;
import com.padel.padeltournament.service.TournamentService;
import com.padel.padeltournament.model.Match;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

  private final TournamentService service;

  public TournamentController(TournamentService service) {
    this.service = service;
  }

  /**
   * Create a new Tournemant and set it as active in the "game manager" (now only
   * one client can start a tournament)
   */
  @PostMapping
  public void createTournament() {
    Tournament tournament = new Tournament("empty");
    service.setActiveTournament(tournament);
  }

  /**
   * Start the tournament and gives a name, Players should already been added
   * beforehand
   *
   * @param name of tournament
   */
  @PostMapping("/start")
  public void startTournament(@RequestBody String name) {
    service.startTournament(name);
  }

  /**
   * Add a team to the currently active tournament
   * 
   * @param team
   */
  @PostMapping("/add-team")
  public void addTeam(@RequestBody Team team) {
    service.addTeamTournament(team);
  }

  @PostMapping("/remove-team")
  public void removeTeam(@RequestBody int index) {
    System.out.println("index: " + index + " should be removed acoording to frontend");
    service.removeTeamTournament(index);
  }

  /**
   * Get all the teams currently in the tournament
   * 
   * @return a list of all the teams
   */
  @GetMapping("/get-teams")
  public List<Team> getTeams() {
    return service.getTeamsTournament();
  }

  /**
   * Get all the teams currently in the tournament
   * 
   * @return a list of all the teams
   */
  @GetMapping("/get-standing")
  public List<Team> getStanding() {
    System.out.println("asked for standing");
    return service.getStandingTournament();
  }

  @GetMapping("/get-nextmatches")
  public List<Match> getNextMathes() {
    System.out.println("Asked for nextmatches");
    return service.getNextMatches();
  }

  @PostMapping("/update-matches")
  public void updateMatches(@RequestBody List<Match> matches) {
    matches.forEach(m -> System.out.println(
        m.getTeam1().getName() + " [" + m.getTeam1score() + "] vs " +
            m.getTeam2().getName() + " [" + m.getTeam2score() + "]"));
    service.updateMatchesTournament(matches);
  }

  @GetMapping("/get-name")
  public String getTournamentName() {
    return service.getTournamentName();
  }

}
