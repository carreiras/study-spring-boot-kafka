package com.ewecarreiras.shopapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ewecarreiras.shopapi.dto.ShopItemDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "shop_item")
public class ShopItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_identifier")
    private String productIdentifier;

    private Integer amount;

    private Float price;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public static ShopItem convert(ShopItemDto shopItemDto) {
        ShopItem shopItem = new ShopItem();
        shopItem.setProductIdentifier(shopItemDto.getProductIdentifier());
        shopItem.setAmount(shopItemDto.getAmount());
        shopItem.setPrice(shopItemDto.getPrice());
        return shopItem;
    }
}
