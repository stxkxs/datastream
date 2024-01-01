# kind/kubernetes

```shell
# create kind cluster
https://github.com/stxkxs/data-cluster

# configure kubectl context
kubectl config set-context data-cluster --cluster=data-cluster

# load docker image 
## ./kind-deploy.sh
mvn clean install -DskipTests
docker build -t proto/datastream:0.1 .
kind load docker-image proto/datastream:0.1 --name data-cluster
kubectl apply -f deployment.local.yaml

# app
kubectl port-forward service/datastream 8080:8080 -n api
curl -v -X POST -H "Content-Type:application/json" localhost:8080/v1/wikimedia/emit/streams\?total=25 -d '{"streams":["recentchange","page-create"]}'
curl -v -X POST localhost:8080/v1/wikimedia/emit/test\?total=10
curl -v -H 'Accept: application/openmetrics-text; version=1.0.0; charset=utf-8' localhost:8080/actuator/prometheus
curl -v localhost:8080/actuator/health
curl -v localhost:8080/actuator/info

# grafana
## username: admin
## password: prom-operator
kubectl port-forward service/kube-prometheus-stack-grafana 8888:80 -n o11y
http://localhost:8888

# prometheus
kubectl port-forward service/kube-prometheus-stack-prometheus 9090:9090 -n o11y
http://localhost:9090/graph

# kafka
## create kafka-util pod
kubectl run kafka-util --image=ubuntu:latest -i --tty
apt update -y
apt install curl -y
apt install default-jre -y

## configure auth
cat << EOF > client.properties
bootstrap.servers=kafka.kafka.svc.cluster.local:9092
security.protocol=SASL_PLAINTEXT
sasl.mechanism=PLAIN
sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username='datastream-user' password='datastream-password';
EOF

## download kafka tools
curl -L -o kafka.tgz https://downloads.apache.org/kafka/3.6.1/kafka_2.13-3.6.1.tgz
tar -xzf kafka.tgz

# usage
./kafka_2.13-3.6.1/bin/kafka-topics.sh --list --bootstrap-server kafka.kafka.svc.cluster.local:9092 --command-config client.properties
./kafka_2.13-3.6.1/bin/kafka-console-consumer.sh --bootstrap-server kafka.kafka.svc.cluster.local:9092 --topic WIKIMEDIA_TEST_EVENTS --from-beginning --command-config client.properties
```