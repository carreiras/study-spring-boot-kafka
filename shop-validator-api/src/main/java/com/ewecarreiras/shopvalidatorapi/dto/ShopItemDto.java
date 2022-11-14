package com.ewecarreiras.shopvalidatorapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopItemDto {

    private String productIdentifier;
    private Integer amount;
    private Float price;
}
