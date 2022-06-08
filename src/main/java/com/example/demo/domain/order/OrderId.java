package com.example.demo.domain.order;

import lombok.Value;

@Value
public class OrderId {
  Long value;

  public static final OrderId NONE = new OrderId(null);
}
