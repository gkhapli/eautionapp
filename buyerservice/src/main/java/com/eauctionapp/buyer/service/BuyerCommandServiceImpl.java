package com.eauctionapp.buyer.service;


import com.eauctionapp.buyer.model.Buyer;
import com.eauctionapp.buyer.repository.BuyerCommandRepository;
import com.eauctionapp.common.dto.UserInfoCommandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerCommandServiceImpl implements BuyerCommandService {

    @Autowired
    private BuyerCommandRepository buyerCommandRepository;

    @Override
    public Buyer addBuyer(UserInfoCommandDTO userInfoCommandDTO) {
        Buyer buyer = Buyer.builder()
                .firstName(userInfoCommandDTO.getFirstName())
                .lastName(userInfoCommandDTO.getLastName())
                .address(userInfoCommandDTO.getAddress())
                .city(userInfoCommandDTO.getCity())
                .email(userInfoCommandDTO.getEmail())
                .phone(userInfoCommandDTO.getPhone())
                .pin(userInfoCommandDTO.getPin())
                .state(userInfoCommandDTO.getState())
                .build();
        buyerCommandRepository.insert(buyer);
        return buyer;
    }
}
