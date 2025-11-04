# Restaurant Service Configuration

This document describes the configuration settings for the **Order Service** in the Food Ordering System. The service integrates with Kafka for event-driven communication, uses PostgreSQL for persistence, and is built with Spring Boot.

---

## 🖥️ Server Configuration

| Property | Description |
|---------|-------------|
| `server.port` | Port on which the service runs (default: `8183`) |

---

## 📋 Logging

| Property | Description |
|---------|-------------|
| `logging.level.com.food.ordering.system` | Sets the logging level for the application package (default: `DEBUG`) |

---

## 🧾 Restaurant Service Topics

These Kafka topics are used for inter-service communication.

| Property | Description |
|---------|-------------|
| `restaurant-approval-request-topic-name` | Topic for sending restaurant approval requests |
| `restaurant-approval-response-topic-name` | Topic for receiving restaurant approval responses |
| `outbox-scheduler-fixed-rate` | Scheduler interval in milliseconds (default: `10000`) |
| `outbox-scheduler-initial-delay` | Initial delay before scheduler starts (default: `10000`) |

---

## 🌱 Spring Configuration

### SQL Initialization

| Property | Description |
|---------|-------------|
| `spring.sql.init.mode` | Always initialize schema |
| `spring.sql.init.schema-locations` | Path to SQL schema file (`classpath:init-schema.sql`) |
| `spring.sql.init.data`               | Path to SQL data file (`classpath:init-data.sql`)   |

### JPA & Hibernate

| Property | Description |
|---------|-------------|
| `spring.jpa.open-in-view` | Disables Open Session in View |
| `spring.jpa.show-sql` | Enables SQL logging |
| `spring.jpa.database-platform` | Hibernate dialect (`PostgreSQLDialect`) |

### Datasource

| Property | Description |
|---------|-------------|
| `spring.datasource.url` | JDBC URL with schema and performance flags |
| `spring.datasource.driver-class-name` | JDBC driver class |
| `spring.datasource.platform` | Database platform (`postgres`) |

---

## 🛠️ Kafka Configuration

### Kafka Cluster

| Property | Description |
|---------|-------------|
| `bootstrap-servers` | Comma-separated list of Kafka brokers |
| `schema-registry-url-key` | Key used for schema registry config |
| `schema-registry-url` | URL of the schema registry |
| `num-of-partitions` | Default number of partitions per topic |
| `replication-factor` | Replication factor for Kafka topics |

### Kafka Producer

| Property | Description                                                                                                                               |
|---------|-------------------------------------------------------------------------------------------------------------------------------------------|
| `key-serializer-class` | Serializer for message keys                                                                                                               |
| `value-serializer-class` | Avro serializer for message values                                                                                                        |
| `compression-type` | Compression algorithm (`snappy`) <br/> Balanced encrypting algorith                                                                       |
| `acks` | Acknowledgment level (`all`) <br/> Kafka Producer will wait acknowledgment from each broker <br/> before confirming the produce operation |
| `batch-size` | Base batch size in bytes                                                                                                                  |
| `batch-size-boost-factor` | Boost factor for batch size                                                                                                               |
| `linger-ms` | Delay before sending batch                                                                                                                |
| `request-timeout-ms` | Timeout for producer requests                                                                                                             |
| `retry-count` | Number of retries on failure                                                                                                              |

### Kafka Consumer

| Property | Description                                                                                                              |
|---------|--------------------------------------------------------------------------------------------------------------------------|
| `key-deserializer` | Deserializer for message keys                                                                                            |
| `value-deserializer` | Avro deserializer for message values                                                                                     |
| `payment-consumer-group-id` | Group ID for payment topic - <br/> Mainly used to avoid reading data from the start <br/>when reseting the kafka service |
| `restaurant-approval-consumer-group-id` | Group ID for restaurant approval topic                                                                                   |
| `customer-group-id` | Group ID for customer topic                                                                                              |
| `auto-offset-reset` | Offset reset policy (`earliest`)                                                                                         |
| `specific-avro-reader-key` | Key for Avro reader config                                                                                               |
| `specific-avro-reader` | Enables specific Avro reader                                                                                             |
| `batch-listener` | Enables batch consumption                                                                                                |
| `auto-startup` | Automatically starts consumers                                                                                           |
| `concurrency-level` | Number of concurrent consumer threads -<br/> Must match with number of partitions for max performance                    |
| `session-timeout-ms` | Session timeout for consumer group                                                                                       |
| `heartbeat-interval-ms` | Heartbeat interval for consumer group                                                                                    |
| `max-poll-interval-ms` | Max interval between polls                                                                                               |
| `max-poll-records` | Max records per poll                                                                                                     |
| `max-partition-fetch-bytes-default` | Default fetch size per partition                                                                                         |
| `max-partition-fetch-bytes-boost-factor` | Boost factor for fetch size                                                                                              |
| `poll-timeout-ms` | Poll timeout duration                                                                                                    |
