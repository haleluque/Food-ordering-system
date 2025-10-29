# 🍽️ Kafka-Based Food Ordering System – Setup & Operations Guide

This guide provides all the essential commands to run, manage, and monitor the Kafka-powered food ordering system using Docker Compose, Kubernetes, Helm, and networking tools like Netcat.

Remember to run these commands inside the "docker-compose" folder in the infrastructure folder of the project.
The required order is the following:
- Run Zookeeper command
- Run Kafka Cluster command
- Initialize Kafka Topics command

If error encountered, and the 'volumes/kafka' and 'volumes/zookeeper' folders have data, empty them and re-run the commands

---

## 📦 Docker Compose

### 🐘 Run Zookeeper
```bash
docker-compose -f common.yml -f zookeeper.yml up
```

### ⏹️ Stop Zookeeper
```bash
docker-compose -f common.yml -f zookeeper.yml down
```

### 🧱 Run Kafka Cluster
```bash
docker-compose -f common.yml -f kafka_cluster.yml up
```

### ⏹️ Stop Kafka Cluster
```bash
docker-compose -f common.yml -f kafka_cluster.yml down
```

### 🔁 Initialize Kafka Topics
```bash
docker-compose -f common.yml -f init_kafka.yml up --force-recreate
```

---

## ❤️ Zookeeper Health Check
```bash
echo ruok | ncat localhost 2181
```

---

## 📊 Kafka Manager UI
Access the Kafka Manager dashboard:

```
http://localhost:9000/
```
Register the cluster manually:
- Cluster name: food-ordering-system-cluster
- Cluster zookeeper host: zookeeper:2181
---

## 🔁 Netcat (Nmap)

### Terminal 1 – Listen
```bash
ncat -l 9999
```

### Terminal 2 – Connect
```bash
ncat localhost 9999
```

Type a message in one terminal and press Enter — it will appear in the other.

---

## ⛵ Helm – Confluent Kafka

### ▶️ Install Helm Chart
```bash
helm install local-confluent-kafka helm/cp-helm-charts --version 0.6.0
```

### ❌ Uninstall Helm Chart
```bash
helm uninstall local-confluent-kafka
```

---

## 🐳 Docker Utilities

### 🔍 Check for Food Ordering System Images
```bash
docker images | findstr food.ordering.system
```

---

## ☸️ Kubernetes Commands

### 🔍 List All Pods
```bash
kubectl get pods
```

### 📦 Create Kafka Client Pod
```bash
kubectl apply -f kafka-client.yml
```

### 🖥️ Enter Kafka Client Pod
```bash
kubectl exec -it kafka-client -- /bin/bash
```

### 🚀 Deploy Application
```bash
kubectl apply -f application-deployment-local.yaml
```

### 📄 View Logs
```bash
kubectl logs <container_name>
```

### ❌ Delete Kafka Client Pod
```bash
kubectl delete -f kafka-client.yml
```

---

## 🧵 Kafka Topics

### 📜 List All Topics
```bash
kafka-topics --zookeeper local-confluent-kafka-cp-zookeeper-headless:2181 --list
```

### ➕ Create Topics
```bash
kafka-top