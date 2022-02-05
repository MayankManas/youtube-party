package com.partube.io.models;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.util.UUID;

@Data
@Builder
public class User {
  private UUID uuid;
  private String username;
  private Time lastPollTime;

  public static UserBuilder builder() {
    return new UserBuilder();
  }
}
