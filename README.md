# 🍽️ Food Ordering System – Guide

The following Spring boot project creates abasic foodordering system that works with:
- Microservices
- Clean and Hexagonal Architecture
- DDD (Domain Driven Design)
- SAGA
- Outbox
- CQRS
- Kafka
- Kubernetes and GKE

Check for some useful reousrces in the resources folder.

---

## 📊 Microservices

It is composed by 4 microservices
- Order service
- Restaurant service
- Payment service
- Customer service

Additionally, It has an infrastructure service that contains, docker-compose with kafka modules necessary 
to start up the containers.
---

## 🚀 Set up steps
1. If you want a fresh data start, go to infrastructure/docker-compose/volumes folder, empty the brokers, data and zookeeper folders.
2. Run and start zookeeper, kafka cluster and init-kafka yaml files. More info in the README file in docker-compose folder
3. (Optional) check the kafka-manager to check if the cluster is up and running.
4. Start each one of the microservices, go to SpringBootApplication classes and run them.
5. (Optional) check in the local db if the schema has been created, and the init-data has been inserted
6. Import the postman_collection json to Postman if necessary, and use the endpoints to call the application. Check that the ids match with the ini-data inserted during step 4.

## 🧱 Extra Branches
In this project, you will find multiple branches with different states of the project:

- master: Branch with the final implementation
- before-outbox: Branch with the project before the implementation of Outbox pattern
- before-saga: Branch with the project before the implementation of SAGA pattern
- publish-event-option-1: Branch with the main way of publishing events
- publish-event-option2: Branch with the alternative way of publishing 

You can move to any of them an explore the previous states of the project.