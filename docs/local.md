# local/docker compose

```shell
# start
docker compose up

# usage
curl -v -X POST -H "Content-Type:application/json" localhost:8080/v1/wikimedia/emit/streams\?total=25 -d '{"streams":["recentchange","page-create"]}'
curl -v -X POST localhost:8080/v1/wikimedia/emit/test\?total=10

curl -v -H 'Accept: application/openmetrics-text; version=1.0.0; charset=utf-8' localhost:8080/actuator/prometheus
curl -v localhost:8080/actuator/health
curl -v localhost:8080/actuator/info

# grafana ui
http://localhost:3000

# prometheus admin ui
http://localhost:9090/graph
```
