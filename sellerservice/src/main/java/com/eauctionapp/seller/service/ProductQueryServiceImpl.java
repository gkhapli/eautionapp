package com.eauctionapp.seller.service;

import com.eauctionapp.common.dto.ProductBidQueryDTO;
import com.eauctionapp.seller.repository.ProductQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    @Autowired
    private ProductQueryRepository productQueryRepository;

    @Override
    public List<ProductBidQueryDTO> getBidDetails(Long productId) {
        return productQueryRepository.getProductBidDetails(productId);
    }
}
