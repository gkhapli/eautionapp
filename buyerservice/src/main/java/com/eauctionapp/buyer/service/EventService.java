package com.eauctionapp.buyer.service;

import com.eauctionapp.buyer.event.EventType;
import com.eauctionapp.buyer.model.BidInformation;
import com.eauctionapp.buyer.model.Buyer;

public interface EventService {

    void sendBidInfoEvent(Buyer buyer, BidInformation bidInformation, EventType eventType);
    void sendBidInfoEvent(BidInformation bidInformation, EventType eventType);
}
