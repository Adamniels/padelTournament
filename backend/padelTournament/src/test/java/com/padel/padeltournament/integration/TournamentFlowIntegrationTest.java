package com.padel.padeltournament.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.padel.padeltournament.model.Match;
import com.padel.padeltournament.model.Team;
import com.padel.padeltournament.dto.TournamentStartDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TournamentFlowIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void testFullTournamentFlow() throws Exception {
    // 1. Create a new tournament
    mockMvc.perform(post("/api/tournaments"))
        .andExpect(status().isOk());

    // 2. Add teams
    // TODO: lägg till fler lag
    Team teamA = new Team(1L, "TeamA");
    Team teamB = new Team(2L, "TeamB");

    mockMvc.perform(post("/api/tournaments/add-team")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(teamA)))
        .andExpect(status().isOk());

    mockMvc.perform(post("/api/tournaments/add-team")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(teamB)))
        .andExpect(status().isOk());

    // 3. Start tournament
    TournamentStartDTO dto = new TournamentStartDTO();
    dto.setName("MyTestTournament");
    dto.setCourts(2);
    mockMvc.perform(post("/api/tournaments/start")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isOk());

    // 4. Get next matches
    String response = mockMvc.perform(get("/api/tournaments/get-nextmatches"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].team1.name").value("TeamA"))
        .andExpect(jsonPath("$[0].team2.name").value("TeamB"))
        .andReturn()
        .getResponse()
        .getContentAsString();

    // 5. Send in match result
    Match match = objectMapper.readValue(response, Match[].class)[0];
    match.setTeam1score(6);
    match.setTeam2score(3);
    match.setPlayed(true);

    mockMvc.perform(post("/api/tournaments/update-matches")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(List.of(match))))
        .andExpect(status().isOk());

    // 6. Check standings
    mockMvc.perform(get("/api/tournaments/get-standing"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("TeamA"))
        .andExpect(jsonPath("$[0].wonMatches").value(1))
        .andExpect(jsonPath("$[0].score").value(3))
        .andExpect(jsonPath("$[1].name").value("TeamB"))
        .andExpect(jsonPath("$[1].score").value(-3));
  }
}
