package com.eauctionapp.buyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.eauctionapp.buyer","com.eauctionapp.common"})
public class BuyerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuyerApplication.class, args);
    }
}
