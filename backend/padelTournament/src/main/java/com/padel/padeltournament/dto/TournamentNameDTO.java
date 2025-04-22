package com.padel.padeltournament.dto;

// klass för att ta emot tournament name från frontend
public class TournamentNameDTO {
  private String name;

  public TournamentNameDTO() {
    // tom konstruktor behövs för JSON-deserialisering
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
