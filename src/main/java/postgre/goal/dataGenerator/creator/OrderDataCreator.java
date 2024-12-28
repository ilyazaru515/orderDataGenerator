package postgre.goal.dataGenerator.creator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import postgre.goal.dataGenerator.util.Randomizer;
import postgre.goal.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderDataCreator {
    private final Randomizer randomizer;

    public OrderInfo createDataBatch(List<Customer> customers, List<Good> goods) {
        var orders = new ArrayList<Order>();
        var goodDetails = new ArrayList<OrderDetails>();

        for (Customer customer : customers) {
            if (randomizer.randomInt(3) != 1) {
                var orderCount = randomizer.randomInt(5);
                for (int o = 0; o < orderCount; o++) {
                    var order = new Order();
                    order.setUserId(customer.getUserId());
                    order.setOrderId(UUID.randomUUID());
                    var goodsCount = randomizer.randomInt(10);
                    for(int g = 0; g < goodsCount; g++) {
                        var goodDetail = new OrderDetails();
                        goodDetail.setDetailId(UUID.randomUUID());
                        var good = goods.get(randomizer.randomInt(goods.size()));
                        goodDetail.setGoodId(good.getGoodId());
                        goodDetail.setOrderId(order.getOrderId());
                        goodDetail.setGoodsCount(randomizer.randomInt(15) + 1);
                        goodDetails.add(goodDetail);
                    }
                    orders.add(order);
                }
            }
        }
        var orderInfo = new OrderInfo();
        orderInfo.setOrders(orders);
        orderInfo.setOrderDetails(goodDetails);
        return orderInfo;
    }
}
