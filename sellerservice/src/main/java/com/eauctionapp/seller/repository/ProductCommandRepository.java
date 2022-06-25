package com.eauctionapp.seller.repository;

import com.eauctionapp.seller.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCommandRepository extends JpaRepository<Product,Long> {
}
