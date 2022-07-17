package com.eauctionapp.seller.controller;

import com.eauctionapp.common.dto.ProductBidQueryDTO;
import com.eauctionapp.common.dto.ProductQueryDTO;
import com.eauctionapp.seller.service.ProductQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProductQueryController {

    @Autowired
    private ProductQueryService productQueryService;

    @CrossOrigin
    @GetMapping("/report/show-bids/{productId}")
    public Page<ProductBidQueryDTO> showBids(@PathVariable Long productId,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "3") int size){
        log.info("Get bids for the ProductId {}",productId);
        Pageable paging = PageRequest.of(page, size);
        return productQueryService.getBidDetails(productId,paging);
    }

    @CrossOrigin
    @GetMapping("/report/products")
    public List<ProductQueryDTO> getProductDetails(){
        log.info("Get all the Product Details{}");
        return productQueryService.getProductDetails();
    }

    @CrossOrigin
    @GetMapping("/report/products/{productId}")
    public List<ProductQueryDTO> getProductDetails(@PathVariable Long productId){
        log.info("Get productdetails for the ProductId {}");
        return productQueryService.getProductDetails(productId);
    }
}
