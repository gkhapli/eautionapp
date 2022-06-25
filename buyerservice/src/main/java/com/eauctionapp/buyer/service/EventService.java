package com.eauctionapp.buyer.service;

import com.eauctionapp.buyer.model.BidInformation;
import com.eauctionapp.buyer.model.Buyer;
import com.eauctionapp.common.event.EventType;

public interface EventService {

    void sendBidInfoEvent(Buyer buyer, BidInformation bidInformation, EventType eventType);
    void sendBidInfoEvent(BidInformation bidInformation, EventType eventType);
}
