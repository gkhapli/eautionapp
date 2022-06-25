package com.eauctionapp.seller.service;

import com.eauctionapp.common.event.EventType;
import com.eauctionapp.seller.entity.Product;

public interface EventService {

    void sendProductEvent(Product product, EventType eventType);
}
