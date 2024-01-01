# datastream

spring boot api to seed a kafka topic with wikimedia events. uses micrometer to send metrics, traces, and logs to observabilty oss. oss includes grafana, prometheus, loki, and tempo.

## required

+ [mvn](https://maven.apache.org/download.cgi)
+ [java 21](https://sdkman.io/jdks)
  + [sdkman](https://sdkman.io/install)
+ [docker](https://docs.docker.com/get-docker/)

## local setup

+ [local.md](./docs/local.md)

## kind cluster

+ [kind.md](./docs/kind.md)

## aws eks

+ [eks.md](./docs/eks.md)

_references_

+ https://spring.io/blog/2022/10/12/observability-with-spring-boot-3
+ https://tanzu.vmware.com/developer/guides/observability-reactive-spring-boot-3
+ https://micrometer.io/
+ https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator.metrics
+ https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator.micrometer-tracing
+ https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.logging.custom-log-configuration
+ https://github.com/loki4j/loki-logback-appender
+ https://prometheus.io/docs/introduction/overview/
+ https://grafana.com/docs/tempo/latest/
+ https://grafana.com/docs/grafana/latest/
+ https://grafana.com/docs/loki/latest/
+ https://kafka.apache.org/
