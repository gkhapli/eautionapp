package com.eauctionapp.seller.controller;

import com.eauctionapp.common.dto.ProductCommandDTO;
import com.eauctionapp.common.event.EventType;
import com.eauctionapp.common.exception.CustomException;
import com.eauctionapp.seller.entity.Product;
import com.eauctionapp.seller.entity.UserInformation;
import com.eauctionapp.seller.service.BidInformationQueryService;
import com.eauctionapp.seller.service.EventService;
import com.eauctionapp.seller.service.ProductCommandService;
import com.eauctionapp.seller.service.UserInfoCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@Slf4j
public class ProductCommandController {

    @Autowired
    private ProductCommandService productCommandService;

    @Autowired
    private UserInfoCommandService userInfoCommandService;

    @Autowired
    private EventService eventService;

    @Autowired
    private BidInformationQueryService bidInformationQueryService;

    @CrossOrigin
    @PostMapping(path = "/seller/add-product",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Product postProduct(@RequestBody @Valid ProductCommandDTO productCommandDTO)  {
        log.info("Add product by Seller {}",productCommandDTO.getSeller().getEmail());
        UserInformation seller = userInfoCommandService.addUserInfo(productCommandDTO.getSeller());
        log.debug("Add Seller {}",productCommandDTO.getSeller().getFirstName());
        Product product = productCommandService.insertProduct(productCommandDTO, seller);
        log.debug("Product added sucessfully {}",productCommandDTO.getProductName());
        log.debug("Send ProductCreated Event to Kafka");
        eventService.sendProductEvent(product,EventType.PRODUCTCREATED);
        log.debug("Sent ProductCreated Event to Kafka");
        return product;
    }

    @CrossOrigin
    @DeleteMapping(path = "/seller/delete/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId) {
        log.info("Delete product by Id {}",productId);
        Product product = productCommandService.findProductById(productId);
        log.debug("Found product by Id {}",productId);
        if(product == null){
            log.error("No Product found with the provided productId");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        if(product.getBidEndDate().isBefore(LocalDate.now())){
            log.error("The provided bidenddate should be future date");
            throw new CustomException(CustomException.ErrorCode.BID_END_DATE_INPAST);
        }

        //DOne Check if atleast 1 bid is present on the product if then raise Exception
        log.debug("Check if any bid is present on the productId ");
        Long count = bidInformationQueryService.checkIfBidExistsOnProduct(productId);
        if(count > 0){
            log.error("Bid exists on the productId so the product cannot be deleted.");
            throw new CustomException(CustomException.ErrorCode.BID_EXISTS_ON_PRODUCTID);
        }
        productCommandService.deleteProduct(productId);
        log.debug("The product is deleted sucessfully");
        log.debug("Send ProductDeleted Event to Kafka");
        eventService.sendProductEvent(product,EventType.PRODUCTDELETED);
        log.debug("Sent ProductDeleted Event to Kafka");

        return new ResponseEntity(HttpStatus.OK);
    }
}
