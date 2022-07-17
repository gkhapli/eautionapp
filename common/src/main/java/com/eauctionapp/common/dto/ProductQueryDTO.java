package com.eauctionapp.common.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductQueryDTO {

    private Long id;
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private ProductCategory category;
    private Long startingPrice;
    private LocalDate bidEndDate;
}
