package com.eauctionapp.buyer.repository;

import com.eauctionapp.buyer.model.BidInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BidInfoCommandRepository extends MongoRepository<BidInformation, String> {
    @Query(value = "{ 'productId': ?0 }")
    BidInformation findBidInfoByProductId(Long productId);
}
