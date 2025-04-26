package com.padel.padeltournament.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Match {
  private Team team1;
  private Team team2;
  private int team1score;
  private int team2score;
  private boolean isPlayed;

  public Match() {
  }

  public Match(Team team1, Team team2) {
    this.team1 = team1;
    this.team2 = team2;
    this.isPlayed = false;
  }

  // used for creating a finnished game that we got from the frontend
  public Match(Team team1, Team team2, int team1score, int team2score) {
    if (team1score < 0 || team2score < 0) {
      throw new Error("score can't be nagative");
    }
    this.team1 = team1;
    this.team2 = team2;
    this.team1score = team1score;
    this.team2score = team2score;
    this.isPlayed = true;
  }

  // So database don't mistakanly think think are getters and setters
  @JsonIgnore
  public int getScore() {
    return Math.abs(team1score - team2score);
  }

  @JsonIgnore
  public Team getWinner() {
    int res = team1score - team2score;
    if (res > 0) {
      return team1;
    } else if (res < 0) {
      return team2;
    } else {
      return null;
    }
  }

  @JsonIgnore
  public Team getLoser() {
    int res = team1score - team2score;
    if (res > 0) {
      return team2;
    } else if (res < 0) {
      return team1;
    } else {
      return null;
    }
  }

  // Getters and setters
  public Team getTeam1() {
    return team1;
  }

  public void setTeam1(Team team1) {
    this.team1 = team1;
  }

  public Team getTeam2() {
    return team2;
  }

  public void setTeam2(Team team2) {
    this.team2 = team2;
  }

  public int getTeam1score() {
    return team1score;
  }

  public void setTeam1score(int team1score) {
    this.team1score = team1score;
  }

  public int getTeam2score() {
    return team2score;
  }

  public void setTeam2score(int team2Score) {
    this.team2score = team2Score;
  }

  public boolean isPlayed() {
    return isPlayed;
  }

  public void setPlayed(boolean played) {
    isPlayed = played;
  }

  @Override
  public String toString() {
    return team1 + " vs " + team2 + (isPlayed ? " (" + team1score + "-" + team2score + ")" : " (Not played)");
  }

}
