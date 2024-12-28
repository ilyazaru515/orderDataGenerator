package postgre.goal.dataGenerator.creator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import postgre.goal.dataGenerator.configuration.model.GoodProperties;
import postgre.goal.dataGenerator.data.LinesSaver;
import postgre.goal.dataGenerator.util.Randomizer;
import postgre.goal.model.Good;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoodDataCreator implements DataCreator<Good>{
    private final Randomizer randomizer;
    private final LinesSaver linesSaver;
    private final GoodProperties goodProperties;

    @Override
    public List<Good> createDataBatch() {
        var goods = new ArrayList<Good>();
        for (int i = 0; i < goodProperties.getCount(); i++) {
            var good = new Good();
            good.setGoodName(linesSaver.getGoods().get(randomizer.randomInt(linesSaver.getGoods().size())));
            good.setGoodId(UUID.randomUUID());
            good.setGoodPrice(randomizer.randomDouble(goodProperties.getMinPrice(), goodProperties.getMaxPrice()));
            goods.add(good);
        }
        return goods;
    }
}
