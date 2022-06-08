package com.example.demo.domain.order;

import javax.annotation.Nonnull;

public interface OrderRepository {

  void save(@Nonnull Order order);
}
