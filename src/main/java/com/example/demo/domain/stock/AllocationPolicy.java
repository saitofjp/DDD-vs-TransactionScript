package com.example.demo.domain.stock;

import com.example.demo.domain.order.OrderQuantity;
import com.example.demo.domain.stock.StockAllocator.ExecuteResult;
import javax.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AllocationPolicy {

  boolean inStack;
  boolean allocated;
  boolean deemedAllocated;

  public static AllocationPolicy create() {
    return new AllocationPolicy(false, false, false);
  }

  public void checkIsInStack(
      @Nonnull OrderQuantity orderQuantity,
      @Nonnull StockStatus stockStatus
  ) {
    this.inStack = stockStatus.inStack(orderQuantity);
  }

  public void checkAllocation(@Nonnull ExecuteResult allocationResult) {
    this.allocated = allocationResult == ExecuteResult.SUCCESS;
  }

  public void checkDeemedAllocated(boolean deemedAllocated) {
    this.deemedAllocated = deemedAllocated;
  }

  @Nonnull
  public AllocationState calcState() {
    // inStackなのに、allocated出来ないときもある

    if (allocated) {
      return AllocationState.ALLOCATED;
    }
    if (deemedAllocated) {
      return AllocationState.DEEMED_ALLOCATED;
    }
    return AllocationState.STACK_OUT;
  }


}
