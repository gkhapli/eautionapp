package com.eauctionapp.buyer.service;

import com.eauctionapp.buyer.dto.UserInfoCommandDTO;
import com.eauctionapp.buyer.model.Buyer;

public interface BuyerCommandService {
    Buyer addBuyer(UserInfoCommandDTO userInfoCommandDTO);
}
