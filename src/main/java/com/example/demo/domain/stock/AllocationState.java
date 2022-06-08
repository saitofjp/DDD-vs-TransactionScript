package com.example.demo.domain.stock;

/**
 * 在庫割り当て
 * みなし割り当て
 * 売り切れ
 */
public enum AllocationState {
  ALLOCATED,
  DEEMED_ALLOCATED,
  STACK_OUT;

  public boolean isStackOut() {
    return this  == STACK_OUT;
  }
}
