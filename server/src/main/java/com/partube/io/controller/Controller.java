package com.partube.io.controller;

import com.partube.io.dto.UserRequestObject;
import com.partube.io.models.Pool;
import com.partube.io.service.ManagePools;
import com.partube.io.service.ManageUsers;
import com.partube.io.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Slf4j
@org.springframework.stereotype.Controller
public class Controller {
  @Autowired Utilities utils;
  @Autowired ManagePools poolManager;
  @Autowired ManageUsers userManager;

  @GetMapping(value = "/status/")
  public ResponseEntity<Object> getStatus() {
    return ResponseEntity.ok().body("service is up!");
  }

  @PostMapping(value = "/create-pool/")
  public ResponseEntity<Object> createPool(@RequestParam String uuid) {
    try {
      UUID adminUuid = UUID.fromString(uuid);
      log.info("create pool request came for uuid : {}", adminUuid);
      poolManager.createPool(adminUuid);
      return utils.responseEntityWrapper(HttpStatus.OK, "pool created with admins uuid: " + uuid);
    } catch (IllegalArgumentException e) {
      String errorMessage = "Error parsing uuid : " + uuid;
      log.error(errorMessage, e.getMessage());
      return utils.responseEntityWrapper(HttpStatus.BAD_REQUEST, errorMessage);
    }
  }

  @PostMapping(value = "/update-user/")
  public ResponseEntity<Object> updateUser(@RequestBody UserRequestObject userRequestObject) {
    userManager.updateUser(userRequestObject);
    return utils.responseEntityWrapper(HttpStatus.OK, "user updated");
  }

  @PostMapping(value = "/create-user/")
  public ResponseEntity<Object> createUser(@RequestBody UserRequestObject userRequestObject) {
    userManager.addUsers(userRequestObject);
    return utils.responseEntityWrapper(HttpStatus.OK, "user created");
  }

  @GetMapping(value = "/get-pool-info/")
  public ResponseEntity<Object> getPoolInfo(@RequestParam UUID poolId) {
    Object poolInfo = poolManager.getJsonifiedPoolInfo(poolId);
    if (poolInfo != null) {
      return ResponseEntity.ok().body(poolInfo);
    }
    String errorMessage = "No pool with following user id found " + poolId;
    log.error(errorMessage);
    return utils.responseEntityWrapper(HttpStatus.BAD_REQUEST, errorMessage);
  }

  @GetMapping(value = "/invite-user/")
  public ResponseEntity<Object> inviteUser(
      @RequestParam UUID poolId, @RequestParam UUID inviteeId, @RequestParam UUID hostId) {
    Pool pool = poolManager.getPool(poolId);
    if (pool.getAdmin().equals(hostId)) {
      return (poolManager.addUserToPool(poolId, hostId, inviteeId))
          ? utils.responseEntityWrapper(HttpStatus.OK, "user added to pool")
          : utils.responseEntityWrapper(HttpStatus.BAD_REQUEST, "not a valid user");
    } else
      return utils.responseEntityWrapper(
          HttpStatus.UNAUTHORIZED, "user not authorized to perform this action");
  }
}
