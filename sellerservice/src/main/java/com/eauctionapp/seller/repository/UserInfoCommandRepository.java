package com.eauctionapp.seller.repository;

import com.eauctionapp.seller.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoCommandRepository extends JpaRepository<UserInformation,Long> {
}
