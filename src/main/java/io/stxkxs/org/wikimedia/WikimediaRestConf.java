package io.stxkxs.org.wikimedia;

import io.stxkxs.org.wikimedia.model.RestClientConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class WikimediaRestConf {

  @Bean
  public RestClient wikimediaRestClient(RestClientConf conf) {
    return RestClient.builder()
      .requestFactory(new JdkClientHttpRequestFactory())
      .baseUrl(conf.host())
      .defaultHeaders((headers) -> {
        headers.add("Accept", conf.accept());
        headers.add("Accept-Encoding", conf.acceptEncoding());
        headers.add("Api-User-Agent", conf.apiUserAgent());
      })
      .build();
  }
}
