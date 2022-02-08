package com.partube.io.models;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.util.UUID;

@Data
@Builder
public class User {
  private UUID userId;
  private String username;
  private Time lastSyncTime;
  private String messagePhrase;

  public static UserBuilder builder() {
    return new UserBuilder();
  }
}
