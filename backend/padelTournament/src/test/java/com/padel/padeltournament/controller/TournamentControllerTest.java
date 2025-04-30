
package com.padel.padeltournament.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padel.padeltournament.model.Team;
import com.padel.padeltournament.service.TournamentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TournamentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private TournamentService service;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void testAddTeam() throws Exception {
    Team team = new Team(1L, "Team A");

    mockMvc.perform(post("/api/tournaments/add-team")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(team)))
        .andExpect(status().isOk());

    verify(service).addTeamTournament(any(Team.class));
  }

  @Test
  public void testGetTeams() throws Exception {
    when(service.getTeamsTournament()).thenReturn(List.of(new Team(1L, "Team A")));

    mockMvc.perform(get("/api/tournaments/get-teams"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Team A"));
  }
  // TODO: Testa mer
}
