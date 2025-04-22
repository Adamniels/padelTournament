
package com.padel.padeltournament.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@Converter
public class TeamListConverter implements AttributeConverter<List<Team>, String> {
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(List<Team> list) {
    try {
      return objectMapper.writeValueAsString(list);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Team> convertToEntityAttribute(String json) {
    try {
      return objectMapper.readValue(json,
          objectMapper.getTypeFactory().constructCollectionType(List.class, Team.class));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
