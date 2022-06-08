package com.example.demo.domain.product;

import com.example.demo.domain.share.DataTime;
import lombok.Value;

@Value
public class Product {

  ProductId productId;
  ItemNumber itemNumber;

  ProductDetail detail;

  DataTime registered;
}
