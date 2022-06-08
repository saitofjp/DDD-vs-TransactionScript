package com.example.demo.exception;

import com.example.demo.domain.product.ProductId;

public class ResourceNotFound extends AppException {

  public ResourceNotFound(ProductId productId) {
    super(String.valueOf(productId.getValue()));
  }
}
