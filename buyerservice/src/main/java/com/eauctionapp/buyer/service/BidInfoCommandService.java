package com.eauctionapp.buyer.service;

import com.eauctionapp.buyer.dto.BidInformationCommandDTO;
import com.eauctionapp.buyer.model.BidInformation;
import com.eauctionapp.buyer.model.Buyer;

public interface BidInfoCommandService {

    BidInformation placeBid(BidInformationCommandDTO bidInformationCommandDTO, Buyer buyer);

    BidInformation updateBid(Long productId, String buyerEmailld, String newBidAmount);
}
