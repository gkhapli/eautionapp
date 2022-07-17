package com.eauctionapp.buyer.controller;

import com.eauctionapp.buyer.model.BidInformation;
import com.eauctionapp.buyer.model.Buyer;
import com.eauctionapp.buyer.service.BidInfoCommandService;
import com.eauctionapp.buyer.service.BuyerCommandService;
import com.eauctionapp.buyer.service.EventService;
import com.eauctionapp.common.dto.BidInformationCommandDTO;
import com.eauctionapp.common.event.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BuyerCommandController {

    @Autowired
    private BidInfoCommandService bidInfoCommandService;

    @Autowired
    private BuyerCommandService buyerCommandService;

    @Autowired
    private EventService eventService;

    @CrossOrigin
    @PostMapping(path = "/buyer/place-bid",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void postBid(@RequestBody BidInformationCommandDTO bidInformationCommandDTO) {
        //done Check if  productId must be available for auction ProductStore table
        //done If the bid is placed after the bid end date throw e ProductStore table
        //TODO If a bid is already placed on the product throw e
        Buyer buyer = buyerCommandService.addBuyer(bidInformationCommandDTO.getBuyer());
        BidInformation bidInformation = bidInfoCommandService.placeBid(bidInformationCommandDTO, buyer);
        eventService.sendBidInfoEvent(buyer,bidInformation,EventType.BIDINFOCREATED);
    }

    @CrossOrigin
    @PutMapping (path = "/buyer/update-bid/{productId}/{buyerEmailld}/{newBidAmount}")
    public void updateBid(@PathVariable String productId,
                          @PathVariable String buyerEmailld,
                          @PathVariable String newBidAmount) {
        //TODO If the bid amount is updated after the bid end date throw e
        BidInformation bidInformation = bidInfoCommandService.updateBid(Long.valueOf(productId), buyerEmailld, newBidAmount);
        eventService.sendBidInfoEvent(bidInformation,EventType.BIDINFOUPDATED);
    }

}
