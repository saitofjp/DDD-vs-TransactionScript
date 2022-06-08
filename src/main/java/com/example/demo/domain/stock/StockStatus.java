package com.example.demo.domain.stock;

import com.example.demo.domain.order.OrderQuantity;
import com.example.demo.domain.product.ItemNumber;
import com.example.demo.domain.share.DataTime;
import javax.annotation.Nonnull;
import lombok.Value;

@Value(staticConstructor = "from")
public class StockStatus {

  ItemNumber itemNumber;
  int quantity;
  DataTime updated;

  public boolean inStack( @Nonnull OrderQuantity orderQuantity) {
    return true;
  }
}
