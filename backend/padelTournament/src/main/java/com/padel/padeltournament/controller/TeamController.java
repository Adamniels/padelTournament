package com.padel.padeltournament.controller;

import com.padel.padeltournament.model.Team;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final List<Team> teams = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @GetMapping
    public List<Team> getAllTeams() {
        return teams;
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        System.out.println("create team kallades (debug)");
        team.setId(idCounter.getAndIncrement());
        teams.add(team);
        return team;
    }
}
