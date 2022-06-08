package com.example.demo.rest.impl;

import com.example.demo.exception.AppRestClientException;
import com.example.demo.exception.AppRestServerException;
import com.example.demo.rest.KikanStockService;
import com.example.demo.rest.model.StockRequest;
import com.example.demo.rest.model.StockResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KikanStockServiceImpl implements KikanStockService {

  @Autowired
  RestTemplate restTemplate;

  public static final String URL = "https://jsonplaceholder.typicode.com/posts";

  @Override
  public StockResponse call(StockRequest request)
      throws AppRestClientException, AppRestServerException {
    //restTemplate.getForObject()
    return null;
  }
}
