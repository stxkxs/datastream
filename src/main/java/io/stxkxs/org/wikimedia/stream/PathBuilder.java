package io.stxkxs.org.wikimedia.stream;

import io.stxkxs.org.wikimedia.stream.model.StreamsEventsRequest;
import io.stxkxs.org.wikimedia.stream.model.TestEventsRequest;
import io.stxkxs.org.wikimedia.stream.model.WikimediaRequest;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PathBuilder {

  public static final String DEFAULT_PATH = "/test";
  private final Logger log = LoggerFactory.getLogger(getClass());

  public String build(final WikimediaRequest request) {
    if (request == null)
      return test(null);

    switch (request) {
      case StreamsEventsRequest r -> {
        return streams(r);
      }
      case TestEventsRequest r -> {
        return test(r);
      }
    }
  }

  private String streams(final StreamsEventsRequest r) {
    var path = Optional.ofNullable(r)
      .map(request -> {
        var streams = Strings.join(request.streams(), ',');
        var p = streams.isBlank() ? DEFAULT_PATH : "/" + streams;
        return UriComponentsBuilder.fromPath(p)
          .queryParamIfPresent("last-event-id", Optional.ofNullable(request.lastEventIds()))
          .queryParamIfPresent("since", Optional.ofNullable(request.since()))
          .build()
          .toUriString();
      }).orElse(DEFAULT_PATH);

    log.info("created path for streams events request {}", path);
    return path;
  }

  private String test(final TestEventsRequest r) {
    var path = Optional.ofNullable(r)
      .map(request -> UriComponentsBuilder.fromPath(DEFAULT_PATH)
        .queryParamIfPresent("last-event-id", Optional.ofNullable(request.lastEventIds()))
        .queryParamIfPresent("since", Optional.ofNullable(request.since()))
        .build()
        .toString())
      .orElse(DEFAULT_PATH);

    log.info("created path for test events request {}", path);
    return path;
  }
}
