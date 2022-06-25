package com.eauctionapp.buyer.service;


import com.eauctionapp.buyer.model.Product;
import com.eauctionapp.buyer.repository.ProductQueryRepository;
import com.eauctionapp.common.event.EventType;
import com.eauctionapp.common.event.ProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public final class ConsumerService {

    @Autowired
    private final ProductQueryRepository productQueryRepository;

    public ConsumerService(ProductQueryRepository productQueryRepository) {
        this.productQueryRepository = productQueryRepository;
    }

    @KafkaListener(topics = "product-topic", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ProductEvent productEvent) {
        System.out.println("Message received "+productEvent);
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
