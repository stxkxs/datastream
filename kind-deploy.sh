#!/usr/bin/env bash

mvn clean install -DskipTests
docker build -t proto/datastream:0.1 .
kind load docker-image proto/datastream:0.1 --name data-cluster
kubectl apply -f deployment.local.yaml
