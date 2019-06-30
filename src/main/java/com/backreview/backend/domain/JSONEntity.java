package com.backreview.backend.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JSONEntity<T> {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime timestamp;
  private int status;
  private String path;
  private String message;
  private T data;

  public JSONEntity(){
    this.status = 1;
    this.path = "";
    this.timestamp = LocalDateTime.now();
  }
}
