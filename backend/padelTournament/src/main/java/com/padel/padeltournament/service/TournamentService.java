package com.padel.padeltournament.service;

import org.springframework.stereotype.Service;

import com.padel.padeltournament.model.Tournament;

@Service
public class TournamentService {
    private Tournament activeTournament;

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

}

