package io.stxkxs.org.wikimedia.stream.model;

import java.util.List;

public record TestEventsRequest(List<LastEventId> lastEventIds, String since) implements WikimediaRequest {}
