package com.eauctionapp.seller.service;

import com.eauctionapp.seller.dto.ProductCommandDTO;
import com.eauctionapp.seller.entity.Product;
import com.eauctionapp.seller.entity.UserInformation;
import com.eauctionapp.seller.exception.CustomException;
import com.eauctionapp.seller.repository.ProductCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    @Autowired
    private ProductCommandRepository productCommandRepository;

    @Override
    public Product insertProduct(ProductCommandDTO productCommandDTO, UserInformation seller)  {

        if(productCommandDTO.getBidEndDate().isBefore(LocalDate.now())){
            throw new CustomException(CustomException.ErrorCode.BID_END_DATE_SHOULD_BE_IN_FUTURE);
        }
        Product product = Product.builder()
                .productName(productCommandDTO.getProductName())
                .category(productCommandDTO.getCategory())
                .detailedDescription(productCommandDTO.getDetailedDescription())
                .bidEndDate(productCommandDTO.getBidEndDate())
                .shortDescription(productCommandDTO.getShortDescription())
                .startingPrice(productCommandDTO.getStartingPrice())
                .userInformation(seller)
                .build();
        return productCommandRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {

        productCommandRepository.deleteById(productId);
    }

    @Override
    public Product findProductById(Long productId) {
        return productCommandRepository.findById(productId).get();
    }
}
