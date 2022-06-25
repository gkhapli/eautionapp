package com.eauctionapp.buyer.repository;

import com.eauctionapp.buyer.model.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuyerCommandRepository extends MongoRepository<Buyer, String> {
}
