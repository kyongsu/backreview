package com.backreview.backend.controller;

import com.backreview.backend.domain.ErrorEntity;
import com.backreview.backend.utils.DeveloperHandleException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

  private static final String PATH = "/error";

  public ErrorController() {
  }

  @RequestMapping(path = "/error")
  public ErrorEntity handleError(HttpServletRequest request) {
    // Servlet Error Variable
    String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
    ErrorEntity errorEntity = new ErrorEntity("", requestUri );

    // HttpStatus ReasonPhrase(HttpStatus)
    if (statusCode != null) {
      errorEntity.setMessage(HttpStatus.valueOf(statusCode).getReasonPhrase());
      errorEntity.setCode(String.valueOf(statusCode));
    }

    // CoworkHandleException Check
    if(null != exception && ExceptionUtils.getRootCause(exception.getCause()) instanceof DeveloperHandleException){
      DeveloperHandleException developerHandleException = (DeveloperHandleException) ExceptionUtils.getRootCause(exception.getCause());
      errorEntity.setMessage(developerHandleException.getMessage());
      errorEntity.setCode(developerHandleException.getErrorCode());
    }

    return errorEntity;
  }

  @Override
  public String getErrorPath() {
    return PATH;
  }
}
