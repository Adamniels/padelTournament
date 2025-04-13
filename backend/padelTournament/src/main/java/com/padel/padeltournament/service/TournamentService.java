package com.padel.padeltournament.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.padel.padeltournament.model.Tournament;
import com.padel.padeltournament.model.Team;

@Service
public class TournamentService {
  private Tournament activeTournament = new Tournament("test ska inte vara här");

  public Tournament getActiveTournament() {
    return activeTournament;
  }

  public void setActiveTournament(Tournament tournament) {
    this.activeTournament = tournament;
  }

  public boolean hasActiveTournament() {
    return activeTournament != null;
  }

  public void clearTournament() {
    this.activeTournament = null;
  }

  public void addTeamTournament(Team team) {
    this.activeTournament.addTeam(team);
    System.out.println(
        "members after add: " +
            this.activeTournament.getTeams());
    ;
  }

  public void removeTeamTournament(int index) {
    this.activeTournament.removeTeam(index);
    System.out.println(
        "members after remove: " +
            this.activeTournament.getTeams());
    ;
  }

  public List<Team> getTeamsTournament() {
    if (this.activeTournament == null) {
      throw new Error("no tournament is active");
    }
    return this.activeTournament.getTeams();
  }

  public void startTournament(String name) {
    if (this.activeTournament == null) {
      throw new Error("no tournament is active");
    }
    this.activeTournament.setName(name);

    // Start Tournament
    this.activeTournament.start();

    System.out.println(
        "Tournament created with name: " + name + " and with members:" +
            this.activeTournament.getTeams());
  }

}
