package com.eauctionapp.seller.service;

import com.eauctionapp.common.dto.ProductBidQueryDTO;
import com.eauctionapp.common.dto.ProductQueryDTO;
import com.eauctionapp.seller.repository.ProductBidQueryRepository;
import com.eauctionapp.seller.repository.ProductQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    @Autowired
    private ProductQueryRepository productQueryRepository;

    @Autowired
    private ProductBidQueryRepository productBidQueryRepository;

    @Override
    public Page<ProductBidQueryDTO> getBidDetails(Long productId, Pageable paging) {
        return productBidQueryRepository.getProductBidDetails(productId,paging);
    }

    @Override
    public List<ProductQueryDTO> getProductDetails(){
        return productQueryRepository.getProductDetails();
    }

    @Override
    public List<ProductQueryDTO> getProductDetails(Long productId){
        return productQueryRepository.getProductDetails(productId);
    }
}
