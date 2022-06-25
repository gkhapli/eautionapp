package com.eauctionapp.buyer.service;

import com.eauctionapp.buyer.model.BidInformation;
import com.eauctionapp.buyer.model.Buyer;
import com.eauctionapp.buyer.model.Product;
import com.eauctionapp.buyer.repository.BidInfoCommandRepository;
import com.eauctionapp.buyer.repository.BuyerCommandRepository;
import com.eauctionapp.buyer.repository.ProductQueryRepository;
import com.eauctionapp.common.dto.BidInformationCommandDTO;
import com.eauctionapp.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class BidInfoCommandServiceImpl implements BidInfoCommandService {

    @Autowired
    private BidInfoCommandRepository bidInfoCommandRepository;

    @Autowired
    private BuyerCommandRepository buyerCommandRepository;

    @Autowired
    private ProductQueryRepository productQueryRepository;

    public BidInformation placeBid(BidInformationCommandDTO bidInformationCommandDTO, Buyer buyer) {

        Product product = productQueryRepository.checkIfProductExists(bidInformationCommandDTO.getProductId());
        if(product == null){
            throw new CustomException(CustomException.ErrorCode.NOPRODUCTIDPRESENT);
        }

        Product product1 = productQueryRepository.getBidEndDate(bidInformationCommandDTO.getProductId());
        if(LocalDate.now().isAfter(product1.getBidEndDate())){
            throw new CustomException(CustomException.ErrorCode.BID_END_DATE_INPAST_BID);
        }
        BidInformation bidInformation = BidInformation.builder()
                .bidAmount(bidInformationCommandDTO.getBidAmount())
                .productId(bidInformationCommandDTO.getProductId())
                .buyer(buyer)
                .build();
        return bidInfoCommandRepository.insert(bidInformation);
    }

    public BidInformation updateBid(Long productId, String buyerEmailld, String newBidAmount) {
        BidInformation bidInformation = bidInfoCommandRepository.findBidInfoByProductId(productId);

       log.info("Before"+bidInformation.toString());
       Product product = productQueryRepository.getBidEndDate(productId);
       if(LocalDate.now().isAfter(product.getBidEndDate())){
           throw new CustomException(CustomException.ErrorCode.BID_END_DATE_INPAST_BID);
       }
       /*if(bidInformation == null){
           throw new CustomException(CustomException.ErrorCode.NO_BID_FOUND_FOR_PRODUCTID);
       }*/
        BidInformation newBidInformation = BidInformation.builder()
                .buyer(bidInformation.getBuyer())
                .bidAmount(Long.valueOf(newBidAmount))
                .id(bidInformation.getId())
                .productId(bidInformation.getProductId())
                .build();

       return bidInfoCommandRepository.save(newBidInformation);
    }
}
