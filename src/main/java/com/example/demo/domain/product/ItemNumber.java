package com.example.demo.domain.product;

import javax.annotation.Nonnull;
import lombok.Value;

@Value(staticConstructor = "from")
public class ItemNumber {

  String productNumber;
  String modelNumber;

  @Nonnull
  public String getValue() {
    return String.format("%s-%s", productNumber, modelNumber);
  }
}
