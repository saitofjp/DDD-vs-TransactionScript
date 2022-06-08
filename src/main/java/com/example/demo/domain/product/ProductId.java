package com.example.demo.domain.product;

import lombok.Value;

@Value(staticConstructor = "from")
public class ProductId {
  long value;
}
