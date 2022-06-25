package com.eauctionapp.seller.service;

import com.eauctionapp.seller.entity.Product;
import com.eauctionapp.seller.event.EventType;

public interface EventService {

    void sendProductEvent(Product product, EventType eventType);
}
