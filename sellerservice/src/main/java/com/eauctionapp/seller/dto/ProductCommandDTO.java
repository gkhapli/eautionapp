package com.eauctionapp.seller.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductCommandDTO implements Serializable {

    @NotEmpty
    @Size(min = 5,max = 30,message = "Product Name should have min 5 max 30 characters")
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    private ProductCategory category;
    private Long startingPrice;
    private LocalDate bidEndDate;
    private UserInfoCommandDTO seller;
}
