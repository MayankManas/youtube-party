package com.partube.io.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRequestObject {
  private UUID userId;
  private String username;
}
