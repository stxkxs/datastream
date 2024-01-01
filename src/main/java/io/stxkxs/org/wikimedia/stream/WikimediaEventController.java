package io.stxkxs.org.wikimedia.stream;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.observation.annotation.Observed;
import io.stxkxs.org.wikimedia.model.KafkaTopic;
import io.stxkxs.org.wikimedia.stream.model.StreamsEventsRequest;
import io.stxkxs.org.wikimedia.stream.model.TestEventsRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/wikimedia/emit")
@AllArgsConstructor
public class WikimediaEventController {

  private final Logger log = LoggerFactory.getLogger(getClass());
  private final MeterRegistry meterRegistry;
  private final WikimediaService wikimediaService;

  @PostMapping("/streams")
  @Observed(name = "wikimedia.streams",
    contextualName = "produce.kafka.wikimedia.streams.request.begin")
  public ResponseEntity<Void> getStreamsEvents(@RequestParam(defaultValue = "10") int total, @RequestBody StreamsEventsRequest request) {
    log.info("producing kafka wikimedia events: {}", request);

    Gauge.builder("wikimedia.streams.count", () -> request.streams().size())
      .description("number of streams collected and emitted")
      .tag("wikimedia", "streams")
      .tag("kafka", "wikimedia.streams")
      .register(meterRegistry);

    wikimediaService.collect(total, KafkaTopic.WIKIMEDIA_STREAMS_EVENT, request);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/test")
  @Observed(name = "wikimedia.test",
    contextualName = "produce.kafka.wikimedia.test.request.begin")
  public ResponseEntity<Void> getTestEvents(@RequestParam(defaultValue = "10") int total, @RequestBody(required = false) TestEventsRequest request) {
    log.info("producing kafka wikimedia event {}", request);

    Gauge.builder("wikimedia.test.count", () -> 1)
      .description("wikimedia api test count")
      .tag("wikimedia", "streams")
      .tag("kafka", "wikimedia.test")
      .register(meterRegistry);

    wikimediaService.collect(total, KafkaTopic.WIKIMEDIA_TEST_EVENT, request);

    return ResponseEntity.ok().build();
  }
}
