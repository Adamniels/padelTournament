package com.padel.padeltournament.model;

public class Team {
    private Long id;
    private String name;

    public Team() {} // behöver en tom konstruktor också, som är för JSON mappning senare

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
