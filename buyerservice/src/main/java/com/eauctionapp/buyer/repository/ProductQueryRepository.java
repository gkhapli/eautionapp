package com.eauctionapp.buyer.repository;

import com.eauctionapp.buyer.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductQueryRepository extends MongoRepository<Product,Long> {

    @Transactional
    @Query(value = "{ 'productId': ?0 }" , delete = true)
    void deleteByProductId(Long productId);

    @Query(value = "{ 'productId': ?0 }" , fields = "{ 'bidEndDate' : 1 }")
    Product getBidEndDate(Long productId);

    @Query(value = "{ 'productId': ?0 }")
    Product checkIfProductExists(Long productId);
}
