package io.stxkxs.org.wikimedia.model;

public record Properties(int partitions, int replicas, String saslMechanism, String jaasConfig, String callbackHandler) {}
