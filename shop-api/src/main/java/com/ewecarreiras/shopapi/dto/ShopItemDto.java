package com.ewecarreiras.shopapi.dto;

import com.ewecarreiras.shopapi.entity.Shop;
import com.ewecarreiras.shopapi.entity.ShopItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopItemDto {

    private String productIdentifier;

    private Integer amount;

    private Float price;

    private Shop shop;

    public static ShopItemDto convert(ShopItem shopItem) {
        ShopItemDto shopItemDTO = new ShopItemDto();
        shopItemDTO.setProductIdentifier(shopItem.getProductIdentifier());
        shopItemDTO.setAmount(shopItem.getAmount());
        shopItemDTO.setPrice(shopItem.getPrice());
        return shopItemDTO;
    }
}
