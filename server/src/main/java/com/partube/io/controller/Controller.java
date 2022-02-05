package com.partube.io.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
@Slf4j
public class Controller {

  @GetMapping(value = "/status/")
  public ResponseEntity<Object> getStatus() {
    return ResponseEntity.ok().body("service is up!");
  }
}
