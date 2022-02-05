package com.partube.io.models;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class Pool {
  private UUID admin;
  private String poolName;
  private Set<User> users;

  public static PoolBuilder builder() {
    return new PoolBuilder();
  }
}
