package com.example.demo.infrastructure;

import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderRepository;
import org.jetbrains.annotations.NotNull;

public class OrderRepositoryImpl implements OrderRepository {

  /**
   * 名前リネームしたほうが良いけど退避のため
   */
  com.example.demo.data.OrderRepository dataOrderRepository;

  @Override
  public void save(@NotNull Order order) {

    com.example.demo.data.model.Order record = conv(order);
    dataOrderRepository.save(record);
  }

  private com.example.demo.data.model.Order conv(Order order) {

    com.example.demo.data.model.Order re = com.example.demo.data.model.Order.builder()
        .itemNumber(order.getItemNumber().getValue())
        .build();
    
    return dataOrderRepository.save(re);
  }
}
