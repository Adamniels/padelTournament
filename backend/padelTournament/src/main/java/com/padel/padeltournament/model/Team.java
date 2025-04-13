package com.padel.padeltournament.model;

public class Team {
  private Long id;
  private String name;
  private int score;

  // Need a empty constructor to, for the JSON mapping (in the future)
  public Team() {
  }

  public Team(Long id, String name) {
    this.id = id;
    this.name = name;
    this.score = 0;
  }

  // Getters & Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setScore(int score) {
    this.score += score;
  }

  public int getScore() {
    return this.score;
  }

  // TODO: overide so they are compared and sorted by their score

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Team team = (Team) o;

    return name != null ? name.equals(team.name) : team.name == null;
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  @Override
  public String toString() {
    return name;
  }
}
