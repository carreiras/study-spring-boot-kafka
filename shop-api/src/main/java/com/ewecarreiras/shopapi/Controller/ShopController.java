package com.ewecarreiras.shopapi.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ewecarreiras.shopapi.dto.ShopDto;
import com.ewecarreiras.shopapi.entity.Shop;
import com.ewecarreiras.shopapi.entity.ShopItem;
import com.ewecarreiras.shopapi.event.KafkaClient;
import com.ewecarreiras.shopapi.repository.ShopRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopRepository shopRepository;
    private final KafkaClient kafkaClient;

    @GetMapping
    public List<ShopDto> getShop() {
        return shopRepository.findAll()
                .stream()
                .map(shop -> ShopDto.convert(shop))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ShopDto saveShop(@RequestBody ShopDto shopDto) {
        shopDto.setIdentifier(UUID.randomUUID().toString());
        shopDto.setDateShop(LocalDate.now());
        // shopDto.setStatus("PENDING");
        Shop shop = Shop.convert(shopDto);
        for (ShopItem shopItem : shop.getItems()) {
            shopItem.setShop(shop);
        }
        shopDto = ShopDto.convert(shopRepository.save(shop));
        kafkaClient.sendMessage(shopDto);
        return ShopDto.convert(shopRepository.save(shop));
    }
}
