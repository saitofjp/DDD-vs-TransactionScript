package com.example.demo.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockRequest {

  String itemNumber;
  String count;
  /**
   * "info" -> 状況 "register" -> 割り当て
   */
  String requestType;
}
