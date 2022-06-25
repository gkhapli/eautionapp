package com.eauctionapp.seller.service;

import com.eauctionapp.common.event.EventType;
import com.eauctionapp.common.event.ProductEvent;
import com.eauctionapp.seller.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private ProductEventProducer productEventProducer;

    @Override
    public void sendProductEvent(Product product, EventType eventType) {
        ProductEvent productEvent = ProductEvent.builder()
                .bidEndDate(product.getBidEndDate())
                .id(product.getId())
                .eventType(eventType)
                .build();
        productEventProducer.sendMessage(productEvent);
    }
}
