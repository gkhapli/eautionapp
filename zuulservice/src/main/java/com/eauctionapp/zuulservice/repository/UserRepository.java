package com.eauctionapp.zuulservice.repository;

import com.eauctionapp.zuulservice.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findByUsername(String userId);
}
