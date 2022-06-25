package com.eauctionapp.seller.repository;

import com.eauctionapp.seller.entity.BidInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface BidInformationQueryRepository extends JpaRepository<BidInformation,Long> {
    @Modifying
    @Transactional
    @Query("delete from BidInformation where bidId=?1")
    void deleteByBidId(String bidId);

    @Modifying
    @Transactional
    @Query("update BidInformation b set b.bidAmount=?2 where b.bidId=?1")
    void updateBidInformation(String bidId, Long newBidAmount);

    @Query("select COUNT(*) from BidInformation where productId=?1")
    Long checkIfBidExistsOnProduct(Long productId);
}
