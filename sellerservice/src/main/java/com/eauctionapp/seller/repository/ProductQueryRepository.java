package com.eauctionapp.seller.repository;

import com.eauctionapp.common.dto.ProductBidQueryDTO;
import com.eauctionapp.common.dto.ProductQueryDTO;
import com.eauctionapp.seller.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductQueryRepository extends CrudRepository<Product,Long> {

    @Query(value = "select new com.eauctionapp.common.dto.ProductQueryDTO(a.id,a.productName,a.shortDescription,a.detailedDescription,\n" +
            "a.category,a.startingPrice,a.bidEndDate) from Product a ")
    List<ProductQueryDTO> getProductDetails();

    @Query(value = "select new com.eauctionapp.common.dto.ProductQueryDTO(a.id,a.productName,a.shortDescription,a.detailedDescription,\n" +
            "a.category,a.startingPrice,a.bidEndDate) from Product a where a.id = ?1")
    List<ProductQueryDTO> getProductDetails(Long productId);
}
