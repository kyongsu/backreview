package com.backreview.backend.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorEntity {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime timestamp;
  private int status;
  private String path;
  private String message;
  private String code;

  public ErrorEntity(){
    this.status = -1;
    this.timestamp = LocalDateTime.now();
  }

  public ErrorEntity(String message, String path) {
    this();
    this.message = message;
    this.path = path;
  }

  public ErrorEntity(String code, String message, String path) {
    this();
    this.code = code;
    this.message = message;
    this.path = path;
  }
}
