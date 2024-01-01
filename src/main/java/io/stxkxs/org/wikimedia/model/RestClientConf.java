package io.stxkxs.org.wikimedia.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wikimedia")
public record RestClientConf(String host, String accept, String acceptEncoding, String apiUserAgent) {}
