package com.example.demo.exception;

public class AppRestServerException extends AppException {

  public AppRestServerException(String message) {
    super(message);
  }

  public AppRestServerException(String message, Throwable cause) {
    super(message, cause);
  }
}
