package com.padel.padeltournament.dto;

// klass för att ta emot tournament name från frontend
public class TournamentStartDTO {
  private String name;
  private int courts;

  public TournamentStartDTO() {
    // tom konstruktor behövs för JSON-deserialisering
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCourts() {
    return courts;
  }

  public void setCourts(int courts) {
    this.courts = courts;
  }
}
