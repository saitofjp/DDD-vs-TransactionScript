package com.example.demo.domain.stock;

import com.example.demo.domain.order.OrderQuantity;
import javax.annotation.Nonnull;

public interface StockAllocator {

  @Nonnull
  ExecuteResult execute(@Nonnull StockStatus stockStatus, @Nonnull OrderQuantity orderQuantity);

  enum ExecuteResult {
    SUCCESS,
    STOCK_OUT
  }
}
