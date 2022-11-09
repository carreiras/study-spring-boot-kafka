package com.ewecarreiras.shopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ewecarreiras.shopapi.entity.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

}
