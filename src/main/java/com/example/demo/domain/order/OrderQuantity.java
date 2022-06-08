package com.example.demo.domain.order;

import lombok.Value;

@Value(staticConstructor = "from")
public class OrderQuantity {

  int value;
}
