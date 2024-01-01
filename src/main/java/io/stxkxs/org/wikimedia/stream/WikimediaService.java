package io.stxkxs.org.wikimedia.stream;

import io.stxkxs.org.wikimedia.EventStreamConsumer;
import io.stxkxs.org.wikimedia.KafkaProducer;
import io.stxkxs.org.wikimedia.model.KafkaTopic;
import io.stxkxs.org.wikimedia.stream.model.WikimediaRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.VirtualThreadTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@AllArgsConstructor
public class WikimediaService {

  private final Logger log = LoggerFactory.getLogger(getClass());
  private final RestClient wikimediaRestClient;
  private final KafkaProducer kafkaProducer;
  private final PathBuilder pathBuilder;

  public void collect(int count, KafkaTopic topic, WikimediaRequest wikimediaRequest) {
    log.info("emitting {} events to kafka topic {}", count, topic);

    new VirtualThreadTaskExecutor().execute(() -> {
      var path = pathBuilder.build(wikimediaRequest);
      wikimediaRestClient
        .get()
        .uri(path)
        .exchange((request, response) -> {
          log.info("wikimedia status code {}", response.getStatusCode());

          new EventStreamConsumer(kafkaProducer)
            .accept(response.getBody(), count, topic.value());

          return response;
        }, true)
        .close();
    });
  }
}
