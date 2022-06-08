package com.example.demo.service.Impl;

import com.example.demo.controller.model.OrderRequest;
import com.example.demo.controller.model.OrderResponse;
import com.example.demo.data.OrderRepository;
import com.example.demo.rest.KikanStockService;
import com.example.demo.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

  private final KikanStockService kikanStockService;
  private final OrderRepository orderRepository;

  @Override
  public OrderResponse order(OrderRequest request) {

    return new OrderResponse();
  }

  @Override
  public List<OrderResponse> getOrders() {
    return new ArrayList<>();
  }

}




















