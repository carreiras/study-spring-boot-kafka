package com.ewecarreiras.shopapi.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ewecarreiras.shopapi.dto.ShopDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaClient {

    private final KafkaTemplate<String, ShopDto> kafkaTemplate;

    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";

    public void sendMessage(ShopDto msg) {
        kafkaTemplate.send(SHOP_TOPIC_NAME, msg);
    }
}
