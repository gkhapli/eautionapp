package com.eauctionapp.buyer.event;

import com.eauctionapp.buyer.dto.UserInfoCommandDTO;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BidEvent implements Serializable {
    private String bidId;
    private Long productId;
    private Long bidAmount;
    private UserInfoCommandDTO buyer;
    private EventType eventType;
}
