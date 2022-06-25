package com.eauctionapp.seller.entity;


import com.eauctionapp.seller.dto.ProductCategory;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private String shortDescription;
    private String detailedDescription;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private Long startingPrice;
    private LocalDate bidEndDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UserInformation userInformation;
}
