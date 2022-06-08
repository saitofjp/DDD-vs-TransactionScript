package com.example.demo.service;

import com.example.demo.controller.model.OrderRequest;
import com.example.demo.controller.model.OrderResponse;
import com.example.demo.exception.AppException;
import java.util.List;

public interface OrderService {

    OrderResponse order(OrderRequest request) throws AppException;

    List<OrderResponse> getOrders();
}
