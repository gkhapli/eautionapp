package com.eauctionapp.seller.service;

import com.eauctionapp.seller.repository.BidInformationQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BidInformationQueryServiceImpl implements BidInformationQueryService {

    @Autowired
    private BidInformationQueryRepository bidInformationQueryRepository;

    @Override
    public Long checkIfBidExistsOnProduct(Long productId) {
        return bidInformationQueryRepository.checkIfBidExistsOnProduct(productId);
    }
}
