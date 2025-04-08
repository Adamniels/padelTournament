package com.padel.padeltournament.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private Integer id; // kan vara null tills man sparar
    private String name;
    private boolean started = false;
    private boolean finished = false;
    private LocalDateTime createdAt;
    private List<Team> teams;
    // TODO: add matches

    // --- Konstruktörer ---

    public Tournament() {
    }

    public Tournament(String name) {
        this.name = name;
        this.started = true;
        this.finished = false;
        this.createdAt = LocalDateTime.now();
        this.teams = new ArrayList<>();
        // starta även turneringen
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
}
