package io.stxkxs;

import io.stxkxs.org.wikimedia.model.KafkaConf;
import io.stxkxs.org.wikimedia.model.RestClientConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableConfigurationProperties({KafkaConf.class, RestClientConf.class})
@SpringBootApplication
public class DatastreamApi {
  public static void main(String[] args) {
    SpringApplication.run(DatastreamApi.class, args);
  }
}
