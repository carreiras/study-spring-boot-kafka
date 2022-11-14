package com.ewecarreiras.shopvalidatorapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {

    private String identifier;
    private LocalDate dateShop;
    private String status;
    private List<ShopItemDto> items = new ArrayList<>();
}
