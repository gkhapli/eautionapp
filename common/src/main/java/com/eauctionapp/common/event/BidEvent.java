package com.eauctionapp.seller.event;

import com.eauctionapp.seller.dto.UserInfoCommandDTO;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BidEvent implements Serializable {
    private String bidId;
    private Long productId;
    private Long bidAmount;
    private UserInfoCommandDTO buyer;
    private EventType eventType;
}
