package com.eauctionapp.buyer.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BidInformationCommandDTO implements Serializable {

    private Long productId;
    private Long bidAmount;
    private UserInfoCommandDTO buyer;
}
