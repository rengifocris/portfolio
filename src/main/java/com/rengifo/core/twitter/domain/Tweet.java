package com.rengifo.core.twitter.domain;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Value
@Builder
public class Tweet implements Serializable {

  private static final long serialVersionUID = 20200427L;

  private Long id;
  private String userName;
  private String text;
  private String photo;

}
