package com.eauctionapp.seller.service;

import com.eauctionapp.common.dto.ProductCommandDTO;
import com.eauctionapp.seller.entity.Product;
import com.eauctionapp.seller.entity.UserInformation;

public interface ProductCommandService {

    Product insertProduct(ProductCommandDTO productCommandDTO, UserInformation seller) ;

    void deleteProduct(Long productId);

    Product findProductById(Long productId);
}
