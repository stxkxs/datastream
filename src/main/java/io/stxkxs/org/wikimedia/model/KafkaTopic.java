package io.stxkxs.org.wikimedia.model;

import lombok.Getter;

@Getter
public enum KafkaTopic {
  WIKIMEDIA_TEST_EVENT("wikimedia.test"), WIKIMEDIA_STREAMS_EVENT("wikimedia.streams");

  private final String value;
  KafkaTopic(String value) {
    this.value = value;
  }
}
