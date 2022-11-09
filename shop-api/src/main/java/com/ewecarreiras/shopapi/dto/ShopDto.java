package com.ewecarreiras.shopapi.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ewecarreiras.shopapi.entity.Shop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDto {
    
    private String identifier;

    private LocalDate dateShop;

    private String status;

    private List<ShopItemDto> items = new ArrayList<>();

    public static ShopDto convert(Shop shop) {
        ShopDto shopDto = new ShopDto();
        shopDto.setIdentifier(shop.getIdentifier());
        shopDto.setDateShop(shop.getDateShop());
        shopDto.setStatus(shop.getStatus());
        shopDto.setItems(shop.getItems()
                .stream()
                .map(i -> ShopItemDto.convert(i))
                .collect(Collectors.toList()));
        return shopDto;
    }
}
