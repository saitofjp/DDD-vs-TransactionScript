package com.example.demo.service.Impl;

import com.example.demo.controller.model.OrderRequest;
import com.example.demo.controller.model.OrderResponse;
import com.example.demo.domain.order.Order;
import com.example.demo.domain.order.OrderEvent;
import com.example.demo.domain.order.OrderNotification;
import com.example.demo.domain.order.OrderQuantity;
import com.example.demo.domain.order.OrderRepository;
import com.example.demo.domain.product.Product;
import com.example.demo.domain.product.ProductId;
import com.example.demo.domain.product.ProductRepository;
import com.example.demo.domain.share.UserId;
import com.example.demo.domain.stock.AllocationPolicy;
import com.example.demo.domain.stock.AllocationState;
import com.example.demo.domain.stock.CanDeemedAllocatedSpecification;
import com.example.demo.domain.stock.StockAllocator;
import com.example.demo.domain.stock.StockAllocator.ExecuteResult;
import com.example.demo.domain.stock.StockStatus;
import com.example.demo.domain.stock.StockStatusFinder;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceDDDImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final ProductRepository productRepository;
  private final StockStatusFinder stockStatusFinder;
  private final StockAllocator stockAllocator;
  private final OrderNotification orderNotification;
  private final CanDeemedAllocatedSpecification canDeemedAllocated;

  @Override
  public OrderResponse order(@Nonnull OrderRequest request)
      throws AppException {

    ProductId productId = productIdFrom(request);
    UserId userId = userIdFrom(request);
    OrderQuantity quantity = quantityFrom(request);

    // ProductIdから Productを取得する
    @Nullable Product product = productRepository.findOr(productId);

    // 登録されてない商品は、Not Foundを返却
    if (product == null) {
      throw new ResourceNotFound(productId);
    }

    // 在庫の割り当てを実行する、いずれかになる。
    // - 割り当てされた
    // - みなし割り当てされた
    // - 在庫切れだった
    AllocationState state = processStockAllocation(quantity, product);

    // 在庫切れなら通知
    if (state.isStackOut()) {

      OrderEvent stockOut = OrderEvent.stockOut(product, userId, quantity);
      orderNotification.notify(stockOut);

      return new OrderResponse();
    }

    // 割り当てできるなら注文を登録
    Order order = Order.init(product, userId, quantity, state);
    orderRepository.save(order);

    // 注文を登録したら通知
    OrderEvent successOrder = OrderEvent.successOrder(order);
    orderNotification.notify(successOrder);

    return new OrderResponse();
  }


  @Nonnull
  private AllocationState processStockAllocation(
      @Nonnull OrderQuantity orderQuantity,
      @Nonnull Product product
  ) throws AppException {

    // 製品品番からストック状況を取得
    StockStatus stockStatus = stockStatusFinder.find(product.getItemNumber());

    // 割り当てポリシーに従って、割り当てをしていく
    // ストック状況から、在庫の確認
    AllocationPolicy policy = AllocationPolicy.create();
    policy.checkIsInStack(orderQuantity, stockStatus);

    // 在庫の割り当てできるなら、割り当てする
    if (policy.isInStack()) {
      ExecuteResult result = stockAllocator.execute(stockStatus, orderQuantity);
      policy.checkAllocation(result);
    }

    // 割り当てができなくて、みなし割り当て出来るか確認する
    if (!policy.isAllocated()) {
      boolean deemedAllocated = canDeemedAllocated.satisfied(product, stockStatus);
      policy.checkDeemedAllocated(deemedAllocated);
    }

    // 割り当て状況を返却
    return policy.calcState();
  }

  @Nonnull
  private OrderQuantity quantityFrom(@Nonnull OrderRequest request) {
    return OrderQuantity.from(request.getCount());
  }

  @Nonnull
  private UserId userIdFrom(@Nonnull OrderRequest request) {
    return UserId.from(request.getUserId());
  }

  @Nonnull
  private ProductId productIdFrom(@Nonnull OrderRequest request) {
    return ProductId.from(request.getProductId());
  }
}




















