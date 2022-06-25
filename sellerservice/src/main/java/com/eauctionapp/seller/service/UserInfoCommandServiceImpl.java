package com.eauctionapp.seller.service;

import com.eauctionapp.common.dto.UserInfoCommandDTO;
import com.eauctionapp.common.dto.UserType;
import com.eauctionapp.seller.entity.UserInformation;
import com.eauctionapp.seller.repository.UserInfoCommandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserInfoCommandServiceImpl implements UserInfoCommandService {

    @Autowired
    private UserInfoCommandRepository userInfoCommandRepository;

    @Override
    public UserInformation addUserInfo(UserInfoCommandDTO userInfoCommandDTO) {
        UserInformation seller = UserInformation.builder()
                .firstName(userInfoCommandDTO.getFirstName())
                .lastName(userInfoCommandDTO.getLastName())
                .address(userInfoCommandDTO.getAddress())
                .city(userInfoCommandDTO.getCity())
                .email(userInfoCommandDTO.getEmail())
                .phone(userInfoCommandDTO.getPhone())
                .pin(userInfoCommandDTO.getPin())
                .state(userInfoCommandDTO.getState())
                .userType(UserType.SELLER)
                .build();
        log.info("Seller is added {}",seller.getEmail());
        userInfoCommandRepository.save(seller);
        return seller;
    }

    public void deleteUserInfo(Long userId){
        log.info("Deleting the userId {}", userId);
        userInfoCommandRepository.deleteById(userId);
    }
}
