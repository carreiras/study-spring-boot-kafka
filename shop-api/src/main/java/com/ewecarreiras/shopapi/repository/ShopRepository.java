package com.ewecarreiras.shopapi.repository;

import com.ewecarreiras.shopapi.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    public Shop findByIdentifier(String identifier);
}
