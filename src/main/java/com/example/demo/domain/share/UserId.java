package com.example.demo.domain.share;

import lombok.Value;

@Value(staticConstructor = "from")
public class UserId {

  long value;
}
