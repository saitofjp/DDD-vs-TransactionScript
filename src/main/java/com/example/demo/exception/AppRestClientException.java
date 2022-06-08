package com.example.demo.exception;

import lombok.Getter;
import org.springframework.web.client.RestClientResponseException;

/**
 * @see RestClientResponseException
 */
public class AppRestClientException extends AppException {

  @Getter
  private RestClientResponseException responseException;
  public AppRestClientException(String message) {
    super(message);
  }

  public AppRestClientException(String message, Throwable cause) {
    super(message, cause);
  }
}
