package com.eauctionapp.seller.repository;

import com.eauctionapp.common.dto.ProductBidQueryDTO;
import com.eauctionapp.seller.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductQueryRepository extends CrudRepository<Product,Long> {

    @Query(value = "select new com.eauctionapp.common.dto.ProductBidQueryDTO(a.id,a.productName,a.shortDescription,a.detailedDescription,\n" +
            "a.category,a.startingPrice,a.bidEndDate,b.bidAmount,c.firstName,c.lastName,c.address,\n" +
            "c.city,c.state,c.pin,c.phone,c.email) from Product a \n" +
            "JOIN BidInformation b ON a.id = b.productId\n" +
            "JOIN UserInformation c ON b.userInformation.id = c.id\n" +
            "where a.id = ?1 order by b.bidAmount desc")
    List<ProductBidQueryDTO> getProductBidDetails(Long productId);
}
