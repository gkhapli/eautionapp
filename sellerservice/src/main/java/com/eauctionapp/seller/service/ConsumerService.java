package com.eauctionapp.seller.service;


import com.eauctionapp.common.dto.UserInfoCommandDTO;
import com.eauctionapp.common.event.BidEvent;
import com.eauctionapp.common.event.EventType;
import com.eauctionapp.seller.entity.BidInformation;
import com.eauctionapp.seller.entity.UserInformation;
import com.eauctionapp.seller.repository.BidInformationQueryRepository;
import com.eauctionapp.seller.repository.UserInfoCommandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public final class ConsumerService {

    @Autowired
    private final BidInformationQueryRepository bidInfoRepository;

    @Autowired
    private final UserInfoCommandRepository userInfoCommandRepository;

    public ConsumerService(BidInformationQueryRepository bidInfoRepository,
                           UserInfoCommandRepository userInfoCommandRepository) {
        this.bidInfoRepository = bidInfoRepository;
        this.userInfoCommandRepository = userInfoCommandRepository;
    }

    @KafkaListener(topics = "bid-topic", containerFactory = "kafkaListenerContainerFactory")
    public void consume(BidEvent bidEvent) {
        log.debug("Message received {}",bidEvent);
        if(EventType.BIDINFOCREATED.equals(bidEvent.getEventType())){
            UserInfoCommandDTO buyer = bidEvent.getBuyer();
            UserInformation userInformation = UserInformation.builder()
                    .firstName(buyer.getFirstName())
                    .lastName(buyer.getLastName())
                    .address(buyer.getAddress())
                    .state(buyer.getState())
                    .pin(buyer.getPin())
                    .phone(buyer.getPhone())
                    .email(buyer.getEmail())
                    .city(buyer.getCity())
                    .userType(buyer.getUserType())
                    .build();
            UserInformation savedUserInformation = userInfoCommandRepository.save(userInformation);
            bidInfoRepository.save(BidInformation.builder()
                    .bidId(bidEvent.getBidId())
                    .bidAmount(bidEvent.getBidAmount())
                    .userInformation(savedUserInformation)
                    .productId(bidEvent.getProductId()).build());
        }else{
            bidInfoRepository.updateBidInformation(bidEvent.getBidId(),
                    bidEvent.getBidAmount());
        }

    }
}
