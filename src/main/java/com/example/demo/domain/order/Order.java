package com.example.demo.domain.order;

import com.example.demo.domain.product.ItemNumber;
import com.example.demo.domain.product.Product;
import com.example.demo.domain.product.ProductDetail;
import com.example.demo.domain.product.ProductId;
import com.example.demo.domain.share.UserId;
import com.example.demo.domain.stock.AllocationState;
import javax.annotation.Nonnull;
import lombok.Value;

@Value
public class Order {

  OrderId orderId;

  UserId userId;
  ProductId productId;
  ItemNumber itemNumber;

  OrderQuantity quantity;
  AllocationState allocationState;

  ProductDetail productDetail;

  @Nonnull
  public static Order init(
      @Nonnull Product product,
      @Nonnull UserId userId,
      @Nonnull OrderQuantity orderQuantity,
      @Nonnull AllocationState state
  ) {
    return new Order(
        OrderId.NONE,
        userId,
        product.getProductId(),
        product.getItemNumber(),
        orderQuantity,
        state,
        product.getDetail()
    );
  }
}
