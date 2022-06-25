package com.eauctionapp.seller.service;

import com.eauctionapp.common.event.ProductEvent;
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
public final class ProductEventProducer {

    private static final Logger logger = LoggerFactory.getLogger(ProductEventProducer.class);

    @Autowired
    private final KafkaTemplate<String, ProductEvent> kafkaTemplate;

    public ProductEventProducer(KafkaTemplate<String, ProductEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(ProductEvent product) {
        String topicName = "product-topic";
        ListenableFuture<SendResult<String, ProductEvent>> future = kafkaTemplate.send(topicName, product);

        //This will check producer result asynchronously to avoid thread blocking
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(@NotNull Throwable throwable) {
                logger.error("Failed to send message", throwable);
            }

            @Override
            public void onSuccess(SendResult<String, ProductEvent> stringStringSendResult) {
                logger.info("Sent message successfully");
            }
        });
    }
}
