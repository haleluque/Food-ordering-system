package com.food.ordering.system.order.service.domain.outbox.scheduler.payment;

import com.food.ordering.system.order.service.domain.outbox.model.payment.OrderPaymentOutboxMessage;
import com.food.ordering.system.order.service.domain.ports.output.message.publisher.payment.PaymentRequestMessagePublisher;
import com.food.ordering.system.outbox.OutboxScheduler;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation class of the OutboxScheduler interface, it will be responsible for the extraction and publishing
 * of the events saved in the local outbox table. Those events are related with the payment-service
 */
@SuppressWarnings("unused")
@Slf4j
@Component
public class PaymentOutboxScheduler implements OutboxScheduler {

    private final PaymentOutboxHelper paymentOutboxHelper;
    private final PaymentRequestMessagePublisher paymentRequestMessagePublisher;

    public PaymentOutboxScheduler(PaymentOutboxHelper paymentOutboxHelper,
                                  PaymentRequestMessagePublisher paymentRequestMessagePublisher) {
        this.paymentOutboxHelper = paymentOutboxHelper;
        this.paymentRequestMessagePublisher = paymentRequestMessagePublisher;
    }

    /**
     * This method will run every ten seconds (check the outbox-scheduler-fixed-rate property),
     * it will extract every event saved on the outbox tables and publish the corresponding events to kafka
     */
    @Override
    @Transactional
    @Scheduled(fixedDelayString = "${order-service.outbox-scheduler-fixed-rate}",
                initialDelayString = "${order-service.outbox-scheduler-initial-delay}")
    public void processOutboxMessage() {

       // Fetching for all events that represents Orders with PENDING or CANCELLING status
       Optional<List<OrderPaymentOutboxMessage>> outboxMessagesResponse =
               paymentOutboxHelper.getPaymentOutboxMessageByOutboxStatusAndSagaStatus(
                       OutboxStatus.STARTED,
                       SagaStatus.STARTED,
                       SagaStatus.COMPENSATING);

       if (outboxMessagesResponse.isPresent() && !outboxMessagesResponse.get().isEmpty()) {
           List<OrderPaymentOutboxMessage> outboxMessages = outboxMessagesResponse.get();
           log.info("Received {} OrderPaymentOutboxMessage with ids: {}, sending to message bus!",
                   outboxMessages.size(),
                   //prints the ids
                   outboxMessages.stream().map(outboxMessage ->
                           outboxMessage.getId().toString()).collect(Collectors.joining(",")));
           outboxMessages.forEach(outboxMessage ->
                   paymentRequestMessagePublisher.publish(outboxMessage, this::updateOutboxStatus));
           log.info("{} OrderPaymentOutboxMessage sent to message bus!", outboxMessages.size());
       }
    }

    /**
     * BiConsumer call back method, it updates the outbox status after receiving the confirmation from Kafka
     * It will receive the OutboxStatus from the KafkaMessageHelper.getKafkaCallback method, depending on if it failed or not
     */
    private void updateOutboxStatus(OrderPaymentOutboxMessage orderPaymentOutboxMessage, OutboxStatus outboxStatus) {
        orderPaymentOutboxMessage.setOutboxStatus(outboxStatus);
        paymentOutboxHelper.save(orderPaymentOutboxMessage);
        log.info("OrderPaymentOutboxMessage is updated with outbox status: {}", outboxStatus.name());
    }
}
