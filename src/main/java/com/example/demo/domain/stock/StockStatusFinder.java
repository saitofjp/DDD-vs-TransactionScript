package com.example.demo.domain.stock;

import com.example.demo.domain.product.ItemNumber;
import com.example.demo.exception.AppException;
import javax.annotation.Nonnull;

public interface StockStatusFinder {

  @Nonnull
  StockStatus find(@Nonnull ItemNumber itemNumber) throws AppException;
}
