package com.padel.padeltournament.controller;

import com.padel.padeltournament.model.Tournament;
import com.padel.padeltournament.service.TournamentService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService service;

    public TournamentController(TournamentService service) {
        this.service = service;
    }

    // TODO: Detta ska även starta turneringen dvs sätta igång allting som behöver
    // sättas igång
    @PostMapping
    public void createTournament(@RequestBody String name) {
        Tournament tournament = new Tournament(name);
        service.setActiveTournament(tournament);
    }

    // TODO: göra start, add-team endpoint
    // create ska köra om man väljer att skapa en ny annars kan man välja att resume
    // en, så det är det jag måste börja med i frontend nästa gång
    // blir nog att hela teamcontrollern försvinner
}
