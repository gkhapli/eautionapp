package com.eauctionapp.buyer.service;

import com.eauctionapp.buyer.model.Buyer;
import com.eauctionapp.common.dto.UserInfoCommandDTO;

public interface BuyerCommandService {
    Buyer addBuyer(UserInfoCommandDTO userInfoCommandDTO);
}
