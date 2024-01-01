# eks


```shell
# !! uses private repo to deploy eks infrastructure

# configure ecr/docker
aws ecr create-repository --repository-name <repository>

# build linux/amd64 image
docker buildx build -t <aws_account_id>.dkr.ecr.<region>.amazonaws.com/<repository> --platform linux/amd64 .

# push image to ecr
aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin <aws_account_id>.dkr.ecr.<region>.amazonaws.com
docker push <aws_account_id>.dkr.ecr.<region>.amazonaws.com/<repository>

# get kafka bootstrap servers
aws kafka get-bootstrap-brokers --cluster-arn <arn>

# deploy
## !! define KAFKA_URL using bootstrap server value
aws eks update-kubeconfig --region <region> --name <cluster>
kubectl apply -f deployment.eks.local

# app
kubectl port-forward -n applications service/datastream 8080:8080
kubectl logs deployment.apps/datastream -n api --follow
```
