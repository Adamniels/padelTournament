package com.padel.padeltournament.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.padel.padeltournament.repository.*;
import com.padel.padeltournament.model.*;

@Service
public class TournamentDBService {

  private final TournamentRepository repository;

  public TournamentDBService(TournamentRepository repository) {
    this.repository = repository;
  }

  public void saveTournament(Tournament tournament) {
    repository.save(tournament);
  }

  public Tournament getTournament(int id) {
    return repository.findById(id).orElse(null);
  }

  public List<Tournament> getAllTournaments() {
    return repository.findAll();
  }

  public void deleteTournament(int id) {
    repository.deleteById(id);
  }
}
