package com.eauctionapp.seller.service;

import com.eauctionapp.seller.dto.ProductBidQueryDTO;

import java.util.List;

public interface ProductQueryService {

    List<ProductBidQueryDTO> getBidDetails(Long productId);
}
