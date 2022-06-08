package com.example.demo.domain.order;

import com.example.demo.domain.product.Product;
import com.example.demo.domain.share.UserId;
import javax.annotation.Nonnull;

public class OrderEvent {

  @Nonnull
  public static OrderEvent stockOut(Product product, UserId userId, OrderQuantity orderQuantity) {
    return null;
  }

  public static OrderEvent successOrder(Order order) {
    return null;
  }
}
