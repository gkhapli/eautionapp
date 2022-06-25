package com.eauctionapp.buyer.service;

import com.eauctionapp.buyer.model.BidInformation;
import com.eauctionapp.buyer.model.Buyer;
import com.eauctionapp.common.dto.UserInfoCommandDTO;
import com.eauctionapp.common.dto.UserType;
import com.eauctionapp.common.event.BidEvent;
import com.eauctionapp.common.event.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private BidEventProducer bidEventProducer;

    @Override
    public void sendBidInfoEvent(Buyer buyer, BidInformation bidInformation, EventType eventType) {
        BidEvent bidEvent = BidEvent.builder()
                .productId(bidInformation.getProductId())
                .bidId(bidInformation.getId())
                .bidAmount(bidInformation.getBidAmount())
                .eventType(eventType)
                .buyer(UserInfoCommandDTO.builder()
                        .firstName(buyer.getFirstName())
                        .lastName(buyer.getLastName())
                        .address(buyer.getAddress())
                        .city(buyer.getCity())
                        .state(buyer.getState())
                        .pin(buyer.getPin())
                        .email(buyer.getEmail())
                        .phone(buyer.getPhone())
                        .userType(UserType.BUYER)
                        .build())
                .build();
        bidEventProducer.sendMessage(bidEvent);
    }

    @Override
    public void sendBidInfoEvent(BidInformation bidInformation, EventType eventType) {
        BidEvent bidEvent = BidEvent.builder()
                .productId(bidInformation.getProductId())
                .bidId(bidInformation.getId())
                .bidAmount(bidInformation.getBidAmount())
                .eventType(eventType)
                .build();
        bidEventProducer.sendMessage(bidEvent);
    }
}
