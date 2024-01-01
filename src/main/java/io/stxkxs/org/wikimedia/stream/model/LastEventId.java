package io.stxkxs.org.wikimedia.stream.model;

public record LastEventId(String topic, int partition, int offset) {}
