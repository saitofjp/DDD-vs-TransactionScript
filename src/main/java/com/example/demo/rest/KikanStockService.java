package com.example.demo.rest;

import com.example.demo.exception.AppRestClientException;
import com.example.demo.exception.AppRestServerException;
import com.example.demo.rest.model.StockRequest;
import com.example.demo.rest.model.StockResponse;

public interface KikanStockService {

  /**
   * 在庫サービスと通信します。
   *
   * ※ 在庫登録時に、衝突すると、レスポンスではなく、AppRestClientExceptionの406として返却されることがある.
   */
  StockResponse call(StockRequest request)
      throws AppRestClientException, AppRestServerException;

}
