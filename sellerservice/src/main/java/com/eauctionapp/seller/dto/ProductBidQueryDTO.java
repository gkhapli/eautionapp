package com.eauctionapp.seller.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductBidQueryDTO {
    private Long id;
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private ProductCategory category;
    private Long startingPrice;
    private LocalDate bidEndDate;
    private Long bidAmount;
    private String buyerFirstName;
    private String buyerLastName;
    private String buyerAddress;
    private String buyerCity;
    private String buyerState;
    private String buyerPin;
    private Long buyerPhone;
    private String buyerEmail;

}
