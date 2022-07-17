package com.eauctionapp.seller.service;

import com.eauctionapp.common.dto.ProductBidQueryDTO;
import com.eauctionapp.common.dto.ProductQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductQueryService {

    Page<ProductBidQueryDTO> getBidDetails(Long productId, Pageable paging);

    List<ProductQueryDTO> getProductDetails();

    List<ProductQueryDTO> getProductDetails(Long productId);
}
