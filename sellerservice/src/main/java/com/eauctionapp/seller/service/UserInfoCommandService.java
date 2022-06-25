package com.eauctionapp.seller.service;

import com.eauctionapp.common.dto.UserInfoCommandDTO;
import com.eauctionapp.seller.entity.UserInformation;

public interface UserInfoCommandService {
    UserInformation addUserInfo(UserInfoCommandDTO userInfoCommandDTO);

    void deleteUserInfo(Long sellerId);
}
