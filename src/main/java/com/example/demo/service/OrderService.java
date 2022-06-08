package com.example.demo.service;

import com.example.demo.controller.model.OrderRequest;
import com.example.demo.controller.model.OrderResponse;
import com.example.demo.exception.AppException;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface OrderService {

  @Nonnull
  OrderResponse order(@Nullable OrderRequest request) throws AppException;
}
