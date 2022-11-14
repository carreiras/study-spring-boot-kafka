package com.ewecarreiras.shopapi.event;

import com.ewecarreiras.shopapi.dto.ShopDto;
import com.ewecarreiras.shopapi.entity.Shop;
import com.ewecarreiras.shopapi.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {

    private final ShopRepository shopRepository;
    private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

    @KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group")
    public void listenShopEvents(ShopDto shopDto) {
        try {
            log.info("Status da compra recebida no tópico: {}.", shopDto.getIdentifier());
            Shop shop = shopRepository.findByIdentifier(shopDto.getIdentifier());
            shop.setStatus(shopDto.getStatus());
            shopRepository.save(shop);
        } catch (Exception e) {
            log.error("Erro no processamento da compra {}", shopDto.getIdentifier());
        }
    }
}
