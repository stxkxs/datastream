package io.stxkxs.org.wikimedia.stream.model;

public sealed interface WikimediaRequest permits StreamsEventsRequest, TestEventsRequest {}
