package com.eauctionapp.buyer.event;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductEvent implements Serializable {

    private EventType eventType;
    private Long id;
    private LocalDate bidEndDate;
}
