package com.eauctionapp.buyer.service;


import com.eauctionapp.buyer.model.Product;
import com.eauctionapp.buyer.repository.ProductQueryRepository;
import com.eauctionapp.common.event.EventType;
import com.eauctionapp.common.event.ProductEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public final class ConsumerService {

    @Autowired
    private final ProductQueryRepository productQueryRepository;

    public ConsumerService(ProductQueryRepository productQueryRepository) {
        this.productQueryRepository = productQueryRepository;
    }

    @KafkaListener(topics = "product-topic", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ProductEvent productEvent) {
        log.debug("Message received {}",productEvent);
        if(EventType.PRODUCTCREATED.equals(productEvent.getEventType())){
            productQueryRepository.save(Product.builder()
                    .productId(productEvent.getId())
                    .bidEndDate(productEvent.getBidEndDate())
                    .build());
        }else{
            productQueryRepository.deleteByProductId(productEvent.getId());
        }

    }
}
