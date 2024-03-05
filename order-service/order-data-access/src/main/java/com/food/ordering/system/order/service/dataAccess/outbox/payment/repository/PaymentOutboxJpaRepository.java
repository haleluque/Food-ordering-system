package com.food.ordering.system.order.service.dataAccess.outbox.payment.repository;

import com.food.ordering.system.order.service.dataAccess.outbox.payment.entity.PaymentOutboxEntity;
import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentOutboxJpaRepository extends JpaRepository<PaymentOutboxEntity, UUID> {

    Optional<List<PaymentOutboxEntity>> findByTypeAndOutboxStatusAndSagaStatusIn(String type,
                                                                                 OutboxStatus outboxStatus,
                                                                                 List<SagaStatus> sagaStatus);

//    @Query("SELECT p FROM PaymentOutboxEntity p WHERE p.type = :type AND p.sagaId = :sagaId AND p.sagaStatus IN :sagaStatus")
//    Optional<PaymentOutboxEntity> findByTypeAndSagaIdAndSagaStatusIn(@Param("type") String type,
//                                                                     @Param("sagaId") UUID sagaId,
//                                                                     @Param("sagaStatus") List<SagaStatus> sagaStatus);

    Optional<PaymentOutboxEntity> findByTypeAndSagaIdAndSagaStatusIn(String type,
                                                                     UUID sagaId,
                                                                     List<SagaStatus> sagaStatus);


    void deleteByTypeAndOutboxStatusAndSagaStatusIn(String type,
                                                    OutboxStatus outboxStatus,
                                                    List<SagaStatus> sagaStatus);

}
