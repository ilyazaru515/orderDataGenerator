package postgre.goal.dataGenerator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import postgre.goal.dataGenerator.configuration.model.CardConfig;
import postgre.goal.dataGenerator.model.Card;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CardDataCreator implements DataCreator<Card, UUID> {
    private final CardConfig cardConfig;
    private final Random RANDOMIZER = new Random();
    private final String NUMBERS = "012345689";

    @Override
    public List<Card> createDataBatchWithForeignKeys(Supplier<Set<UUID>> supplyForeignKeys) {
        return supplyForeignKeys.get().stream()
                .flatMap((userId) -> createCardsOfUser(userId).stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Card> createDataBatch() {
        return new ArrayList<Card>();
    }


    private List<Card> createCardsOfUser(UUID userId) {
        var userCards = new ArrayList<Card>();
        for (int i = 0; i < RANDOMIZER.nextInt(cardConfig.getMaxCardCount()) + 1; i++) {
            var card = new Card()
                    .userId(userId)
                    .cardAmount(ThreadLocalRandom.current().nextDouble(cardConfig.getMinAmount(), cardConfig.getMaxAmount()))
                    .cardNumber(generateCardNumber());
            userCards.add(card);
        }
        return userCards;
    }

    private String generateCardNumber() {
        var cardNumberBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            var index = RANDOMIZER.nextInt(NUMBERS.length());
            cardNumberBuilder.append(NUMBERS, index, index + 1);
        }
        return cardNumberBuilder.toString();
    }
}
