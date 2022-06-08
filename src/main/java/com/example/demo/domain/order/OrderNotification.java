package com.example.demo.domain.order;

import javax.annotation.Nonnull;

public interface OrderNotification {

  /**
   * 通知する. 失敗しても無視される.
   */
  void notify(@Nonnull OrderEvent event);
}
