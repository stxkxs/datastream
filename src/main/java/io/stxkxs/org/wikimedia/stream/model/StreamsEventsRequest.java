package io.stxkxs.org.wikimedia.stream.model;

import java.util.List;

public record StreamsEventsRequest(List<String> streams, List<LastEventId> lastEventIds, String since) implements WikimediaRequest {}
