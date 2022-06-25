package com.eauctionapp.seller.controller;

import com.eauctionapp.seller.dto.ProductBidQueryDTO;
import com.eauctionapp.seller.service.ProductQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ProductQueryController {

    @Autowired
    private ProductQueryService productQueryService;

    @GetMapping("/report/show-bids/{productId}")
    public List<ProductBidQueryDTO> showBids(@PathVariable Long productId){
        log.info("Get bids for the ProductId {}",productId);
        return productQueryService.getBidDetails(productId);
    }
}
