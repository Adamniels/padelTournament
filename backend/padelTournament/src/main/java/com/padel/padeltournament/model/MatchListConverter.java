package com.padel.padeltournament.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 * A converter that automatically transforms a List<Match> to a JSON String
 * for database storage and vice versa.
 * 
 * This is used by JPA/Hibernate to handle complex types like lists,
 * which normally can't be stored directly in a database column.
 */
@Converter
public class MatchListConverter implements AttributeConverter<List<Match>, String> {

  // ObjectMapper from Jackson library for JSON serialization/deserialization
  private final ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Converts a Java List<Match> into a JSON String when saving to the database.
   *
   * @param list the List<Match> to convert
   * @return JSON String representation of the list
   */
  @Override
  public String convertToDatabaseColumn(List<Match> list) {
    try {
      return objectMapper.writeValueAsString(list);
    } catch (Exception e) {
      throw new RuntimeException(e); // If something goes wrong during conversion
    }
  }

  /**
   * Converts a JSON String back into a Java List<Match> when loading from the
   * database.
   *
   * @param json the JSON String stored in the database
   * @return the reconstructed List<Match>
   */
  @Override
  public List<Match> convertToEntityAttribute(String json) {
    try {
      return objectMapper.readValue(
          json,
          objectMapper.getTypeFactory().constructCollectionType(List.class, Match.class));
    } catch (Exception e) {
      throw new RuntimeException(e); // If something goes wrong during conversion
    }
  }
}
