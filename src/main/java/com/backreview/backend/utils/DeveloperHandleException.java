package com.backreview.backend.utils;

public class DeveloperHandleException extends Exception {
  private final String errorCode;

  public DeveloperHandleException(String errorCode) {
    this.errorCode = errorCode;
  }

  public DeveloperHandleException(String errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public DeveloperHandleException(String errorCode, String message, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public DeveloperHandleException(Throwable cause, String errorCode) {
    super(cause);
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
