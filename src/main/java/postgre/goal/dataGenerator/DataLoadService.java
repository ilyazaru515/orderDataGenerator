package postgre.goal.dataGenerator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import postgre.goal.dataGenerator.model.Card;
import postgre.goal.dataGenerator.model.Customer;
import postgre.goal.dataGenerator.model.IssuingPoint;
import postgre.goal.dataGenerator.service.DataCreator;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataLoadService {
    private final DataCreator<Customer, Void> customerDataCreator;
    private final DataCreator<Card, UUID> cardDataCreator;
    private final DataCreator<IssuingPoint, Void> issuingPointDataCreator;

    @PostConstruct
    void loadData() throws IOException {
        var customers = customerDataCreator.createDataBatch();
        var cards = cardDataCreator.createDataBatchWithForeignKeys(() -> customers.stream()
                .map(Customer::getUserId)
                .collect(Collectors.toSet()));
        var issuingPoints = issuingPointDataCreator.createDataBatch();
        int a = 0;
    }
}
