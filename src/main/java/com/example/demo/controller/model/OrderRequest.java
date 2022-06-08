package com.example.demo.controller.model;

import lombok.Data;

@Data
public class OrderRequest {

   long productId;
   long userId;
   int count;
}
