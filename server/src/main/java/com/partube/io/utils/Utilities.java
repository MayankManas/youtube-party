package com.partube.io.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class Utilities {
  public Object getGenericRequestObject(HttpStatus status, String message) {
    HashMap<String, Object> genericObject = new HashMap<>();
    genericObject.put("timestamp", new Date());
    genericObject.put("status", status);
    if (status.isError()) {
      genericObject.put("error", status.getReasonPhrase());
    }
    if (message != null && message.length() > 0) {
      genericObject.put("message", message);
    }
    return genericObject;
  }

  public ResponseEntity<Object> responseEntityWrapper(HttpStatus status, String message) {
    switch (status) {
      case BAD_REQUEST:
        return ResponseEntity.badRequest()
            .body(getGenericRequestObject(HttpStatus.BAD_REQUEST, message));
      case OK:
        return ResponseEntity.ok().body(getGenericRequestObject(HttpStatus.OK, message));
    }
    return ResponseEntity.badRequest()
        .body(getGenericRequestObject(HttpStatus.BAD_REQUEST, message));
  }
}
