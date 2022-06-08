package com.example.demo.domain.stock;

import com.example.demo.domain.product.Product;

public class CanDeemedAllocatedSpecification {

  public boolean satisfied(Product product, StockStatus stockStatus) {
    return false;
  }
}
