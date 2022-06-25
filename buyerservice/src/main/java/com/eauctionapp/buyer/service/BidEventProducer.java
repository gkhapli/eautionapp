package com.eauctionapp.buyer.service;

import com.eauctionapp.buyer.event.BidEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.validation.constraints.NotNull;

@Component
@Slf4j
public final class BidEventProducer {

    private static final Logger logger = LoggerFactory.getLogger(BidEventProducer.class);

    @Autowired
    private final KafkaTemplate<String, BidEvent> kafkaTemplate;

    public BidEventProducer(KafkaTemplate<String, BidEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(BidEvent bidEvent) {
        String topicName = "bid-topic";
        ListenableFuture<SendResult<String, BidEvent>> future = kafkaTemplate.send(topicName, bidEvent);

        //This will check producer result asynchronously to avoid thread blocking
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(@NotNull Throwable throwable) {
                logger.error("Failed to send message", throwable);
            }

            @Override
            public void onSuccess(SendResult<String, BidEvent> stringStringSendResult) {
                logger.info("Sent message successfully");
            }
        });
    }
}
