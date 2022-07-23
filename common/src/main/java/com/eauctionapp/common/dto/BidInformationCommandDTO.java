package com.eauctionapp.common.dto;

import lombok.*;

import javax.validation.Valid;
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
    @Valid
    private UserInfoCommandDTO buyer;
}
