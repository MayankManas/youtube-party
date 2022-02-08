package com.partube.io.service;

import com.partube.io.dto.UserRequestObject;
import com.partube.io.models.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.HashMap;
import java.util.UUID;

@Data
@Slf4j
@Service
public class ManageUsers {
  private HashMap<UUID, User> userMap = new HashMap<>();

  public void addUsers(UserRequestObject user) {
    userMap.put(
        user.getUserId(),
        User.builder()
            .userId(user.getUserId())
            .username(user.getUsername())
            .lastSyncTime(new Time(System.currentTimeMillis()))
            .build());
    log.info("User with username {}", user.getUsername(), " created");
  }

  public void updateUser(UserRequestObject user) {
    userMap.put(
        user.getUserId(),
        User.builder()
            .userId(user.getUserId())
            .username(user.getUsername())
            .lastSyncTime(new Time(System.currentTimeMillis()))
            .build());
    log.info("User with username {}", user.getUsername(), " updated");
  }
}
