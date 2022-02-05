package com.partube.io.controller;

import com.partube.io.service.ManagePools;
import com.partube.io.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Slf4j
@org.springframework.stereotype.Controller
public class Controller {
  @Autowired Utilities utils;
  @Autowired ManagePools poolManager;

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
      return utils.responseEntityWrapper(
          HttpStatus.OK, "pool created with admins uuid: " + uuid);
    } catch (IllegalArgumentException e) {
      String errorMessage = "Error parsing uuid : " + uuid;
      log.error(errorMessage, e.getMessage());
      return utils.responseEntityWrapper(HttpStatus.BAD_REQUEST, errorMessage);
    }
  }
}
