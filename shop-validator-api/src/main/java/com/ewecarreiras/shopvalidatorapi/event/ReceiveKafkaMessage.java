package com.ewecarreiras.shopvalidatorapi.event;

import com.ewecarreiras.shopvalidatorapi.dto.ShopDto;
import com.ewecarreiras.shopvalidatorapi.dto.ShopItemDto;
import com.ewecarreiras.shopvalidatorapi.model.Product;
import com.ewecarreiras.shopvalidatorapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveKafkaMessage {

    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
    private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";
    private final ProductRepository productRepository;
    private final KafkaTemplate<String, ShopDto> kafkaTemplate;

    @KafkaListener(topics = SHOP_TOPIC_NAME, groupId = "group")
    public void listenerShopTopic(ShopDto shopDto) {
        try {
            log.info("Compra recebida no tópico: {}.", shopDto.getIdentifier());
            boolean success = true;
            for (ShopItemDto item : shopDto.getItems()) {
                Product product = productRepository.findByIdentifier(item.getProductIdentifier());
                if (!isValidShop(item, product)) {
                    shopError(shopDto);
                    success = false;
                    break;
                }
            }
            if (success) {
                shopSuccess(shopDto);
            }
        } catch (Exception e) {
            log.error("Erro no processamento da compra {}", shopDto.getIdentifier());
        }
    }

    // valida se a compra possui algum erro
    private boolean isValidShop(ShopItemDto item, Product product) {
        return product != null && product.getAmount() >= item.getAmount();
    }

    // Envia uma mensagem para o Kafka indicando erro na compra
    private void shopError(ShopDto shopDTO) {
        log.info("Erro no processamento da compra {}.", shopDTO.getIdentifier());
        shopDTO.setStatus("ERROR");
        kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDTO);
    }

    // Envia uma mensagem para o Kafka indicando sucesso na compra
    private void shopSuccess(ShopDto shopDto) {
        log.info("Compra {} efetuada com sucesso.", shopDto.getIdentifier());
        shopDto.setStatus("SUCCESS");
        kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, shopDto);
    }
}
