package com.padel.padeltournament.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 * A converter that automatically transforms a List<Team> into a JSON String
 * for database storage, and reconstructs it back into a List<Team> when
 * loading.
 *
 * This allows Hibernate/JPA to store a list of teams inside a single database
 * column.
 */
@Converter
public class TeamListConverter implements AttributeConverter<List<Team>, String> {

  // ObjectMapper from Jackson library handles the JSON serialization and
  // deserialization
  private final ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Converts a Java List<Team> into a JSON String when saving to the database.
   *
   * @param list the List<Team> to convert
   * @return JSON String representation of the list
   */
  @Override
  public String convertToDatabaseColumn(List<Team> list) {
    try {
      return objectMapper.writeValueAsString(list);
    } catch (Exception e) {
      throw new RuntimeException(e); // If an error occurs during conversion
    }
  }

  /**
   * Converts a JSON String back into a Java List<Team> when reading from the
   * database.
   *
   * @param json the JSON String stored in the database
   * @return the reconstructed List<Team>
   */
  @Override
  public List<Team> convertToEntityAttribute(String json) {
    try {
      return objectMapper.readValue(
          json,
          objectMapper.getTypeFactory().constructCollectionType(List.class, Team.class));
    } catch (Exception e) {
      throw new RuntimeException(e); // If an error occurs during conversion
    }
  }
}
