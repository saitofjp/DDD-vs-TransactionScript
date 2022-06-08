package com.example.demo.domain.product;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class ItemNumberTest {

  @Test
  void getValue() {
    ItemNumber itemNumber = ItemNumber.from("product", "model");

    assertThat(itemNumber.getValue(), is("product-model"));
  }
}