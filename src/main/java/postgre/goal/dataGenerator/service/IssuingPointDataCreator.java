package postgre.goal.dataGenerator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import postgre.goal.dataGenerator.configuration.model.IssuingPointConfig;
import postgre.goal.dataGenerator.data.LinesSaver;
import postgre.goal.dataGenerator.model.IssuingPoint;

import java.util.*;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class IssuingPointDataCreator implements DataCreator<IssuingPoint, Void> {
    private final IssuingPointConfig issuingPointConfig;
    private final LinesSaver linesSaver;
    private final Random RANDOMIZER = new Random();

    private IssuingPoint createIssuingPoint() {
        return new IssuingPoint()
                .address(generateAddress())
                .owner(linesSaver.getIndividualBusinessmans().get(RANDOMIZER.nextInt(linesSaver.getIndividualBusinessmans().size())))
                .workSchedule(linesSaver.getSchedules().get(RANDOMIZER.nextInt(linesSaver.getSchedules().size())));
    }

    private String generateAddress() {
        var addressBuilder = new StringBuilder();
        addressBuilder.append(linesSaver.getCities().get(RANDOMIZER.nextInt(linesSaver.getCities().size())));
        addressBuilder.append(", ");
        addressBuilder.append(linesSaver.getStreets().get(RANDOMIZER.nextInt(linesSaver.getStreets().size())));
        addressBuilder.append(", д. ");
        addressBuilder.append(RANDOMIZER.nextInt(200));
        return addressBuilder.toString();
    }

    @Override
    public List<IssuingPoint> createDataBatchWithForeignKeys(Supplier<Set<Void>> foreignKeysSupplier) {
        return createDataBatch();
    }

    @Override
    public List<IssuingPoint> createDataBatch() {
        var issuingPoints = new ArrayList<IssuingPoint>();
        for (int i = 0; i < issuingPointConfig.getCount(); i++) {
            issuingPoints.add(createIssuingPoint());
        }
        return issuingPoints;
    }
}
