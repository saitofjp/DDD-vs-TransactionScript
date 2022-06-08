package com.example.demo.domain.product;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ProductRepository {

  @Nullable
  Product findOr(@Nonnull ProductId productId);
}
