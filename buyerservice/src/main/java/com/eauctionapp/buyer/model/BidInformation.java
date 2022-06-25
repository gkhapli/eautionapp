package com.eauctionapp.buyer.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "bidinfo")
public final class BidInformation {
    @Id
    private String id;
    private Long productId;
    private Long bidAmount;
    @DBRef
    private Buyer buyer;
}
