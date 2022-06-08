package com.example.demo.service.Impl;

import com.example.demo.controller.model.OrderRequest;
import com.example.demo.controller.model.OrderResponse;
import com.example.demo.data.OrderRepository;
import com.example.demo.data.ProductRepository;
import com.example.demo.data.model.Order;
import com.example.demo.data.model.Product;
import com.example.demo.exception.AppException;
import com.example.demo.exception.AppRestClientException;
import com.example.demo.exception.AppRestServerException;
import com.example.demo.mail.SendMailService;
import com.example.demo.rest.KikanStockService;
import com.example.demo.rest.model.Stock;
import com.example.demo.rest.model.StockRequest;
import com.example.demo.rest.model.StockResponse;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceTransactionScriptImpl implements OrderService {

  private final KikanStockService kikanStockService;
  private final OrderRepository orderRepository;
  private final ProductRepository prodcutRepository;
  private final SendMailService sendMailService;

  @Override
  public OrderResponse order(OrderRequest request)
      throws AppException {

    Product product = prodcutRepository.getById(request.getProductId());

    String itemNumber = product.getProductNumber() + "-" + product.getModelNumber();

    // 在庫状況を取得する
    StockRequest stockRequest = StockRequest.builder()
        .itemNumber(itemNumber)
        .count(String.valueOf(request.getCount())) // TODO これいるの？
        .requestType("info")
        .build();
    StockResponse stockResponse = kikanStockService.call(stockRequest);

    if (stockResponse.getStocks() != null && stockResponse.getStocks().get(0) != null) {

      Stock stock = stockResponse.getStocks().get(0);
      // 在庫がリクエストよりも多ければ
      if (Integer.valueOf(stock.getCount()) >= request.getCount()) {

        StockRequest registerRequest = StockRequest.builder()
            .itemNumber(itemNumber)
            .count(String.valueOf(request.getCount()))
            .requestType("register")
            .build();
        StockResponse registerResponse = null;
        try {
          registerResponse = kikanStockService.call(registerRequest);
        } catch (AppRestClientException e) {

          RestClientResponseException responseException = e.getResponseException();
          if (responseException.getRawStatusCode() != 406) {
            throw e;
          }

          // ここは割り当て失敗。仮割り当てできるか判定のため、次へ
        } 

        if (registerResponse != null && registerResponse.getStockStatus() != null) {
          if (registerResponse.getStockStatus().equals("sucess")) {
            // 購入処理

            Order order = Order.builder()
                .itemNumber(itemNumber)
                .kari(false)
                .page(product.getAge())
                .psage(product.getSage())
                .build();

            orderRepository.save(order);

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo();
            msg.setSubject("");
            msg.setText("");
            sendMailService.sendEmail(msg);

          } else {
            if (stockResponse.getStocks().get(0).getUpdated() != null
                && product.getCreated() != null) {

              // 仮割り当て
              Order order = Order.builder()
                  .itemNumber(itemNumber)
                  .kari(true)
                  .kariStatus("//?")
                  .page(product.getAge())
                  .psage(product.getSage())
                  .build();

              orderRepository.save(order);

              SimpleMailMessage msg = new SimpleMailMessage();
              msg.setTo();
              msg.setSubject("");
              msg.setText("");
              sendMailService.sendEmail(msg);
            } else {

              SimpleMailMessage msg = new SimpleMailMessage();
              msg.setTo();
              msg.setSubject("");
              msg.setText("");
              sendMailService.sendEmail(msg);
            }
          }
        } else if (registerResponse == null) {
          if (stockResponse.getStocks().get(0).getUpdated() != null
              && product.getCreated() != null) {

            // 仮割り当て
            Order order = Order.builder()
                .itemNumber(itemNumber)
                .kari(true)
                .kariStatus("//?")
                .page(product.getAge())
                .psage(product.getSage())
                .build();

            orderRepository.save(order);

            // TODO
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo();
            msg.setSubject("");
            msg.setText("");
            sendMailService.sendEmail(msg);
          } else {

            // TODO
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo();
            msg.setSubject("");
            msg.setText("");
            sendMailService.sendEmail(msg);
          }
        } else {
          throw new AppException("response error ?");
        }
      }

    } else {
      // リクエストよりも在庫が足らない場合
      if (stockResponse.getStocks().get(0).getUpdated() != null && product.getCreated() != null) {

        // 仮割り当て
        Order order = Order.builder()
            .itemNumber(itemNumber)
            .kari(true)
            .kariStatus("//?")
            .page(product.getAge())
            .psage(product.getSage())
            .build();

        orderRepository.save(order);

        // TODO
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo();
        msg.setSubject("");
        msg.setText("");
        sendMailService.sendEmail(msg);
      } else {

        // TODO
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo();
        msg.setSubject("");
        msg.setText("");
        sendMailService.sendEmail(msg);
      }

    }
    return new OrderResponse();
  }

}




















