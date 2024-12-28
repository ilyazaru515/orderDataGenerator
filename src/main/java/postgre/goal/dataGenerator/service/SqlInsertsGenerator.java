package postgre.goal.dataGenerator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import postgre.goal.dataGenerator.creator.DataCreator;
import postgre.goal.dataGenerator.creator.OrderDataCreator;
import postgre.goal.model.Customer;
import postgre.goal.model.Good;
import postgre.goal.model.OrderDetails;
import postgre.goal.model.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SqlInsertsGenerator {
    private final DataCreator<Customer> customerDataCreator;
    private final DataCreator<Good> goodDataCreator;
    private final OrderDataCreator orderDataCreator;

    public void generateSqlInserts() throws IOException {
        var customers = customerDataCreator.createDataBatch();
        var goods = goodDataCreator.createDataBatch();
        var orderInfo = orderDataCreator.createDataBatch(customers, goods);

        var absolutePath = Paths.get("").toAbsolutePath();
        writeToFile(absolutePath + "\\db_pg\\changelog\\customers_insert.sql", generateCustomers(customers));
        writeToFile(absolutePath + "\\db_pg\\changelog\\goods_insert.sql", generateGoods(goods));
        writeToFile(absolutePath + "\\db_pg\\changelog\\orders_insert.sql", generateOrders(orderInfo.getOrders()));
        writeToFile(absolutePath + "\\db_pg\\changelog\\order_details_insert.sql", generateOrderDetails(orderInfo.getOrderDetails()));

        System.out.println();
    }

    private String generateCustomers(List<Customer> customers) {
        var builder = new StringBuilder("BEGIN;\n");

        customers.forEach(customer -> {
            builder.append("INSERT INTO orders.customer(user_id,user_name,user_password,email)\n");
            builder.append(String.format("VALUES(UUID '%s','%s','%s','%s');\n",
                    customer.getUserId(),
                    customer.getUserName(),
                    customer.getPassword(),
                    customer.getEmail()));
        });

        builder.append("COMMIT;");
        return builder.toString();
    }

    private String generateGoods(List<Good> goods) {
        var builder = new StringBuilder("BEGIN;\n");

        goods.forEach(good -> {
            builder.append("INSERT INTO orders.good(good_id,good_name,good_price)\n");
            builder.append(String.format("VALUES(UUID '%s','%s',%s);\n",
                    good.getGoodId(),
                    good.getGoodName(),
                    good.getGoodPrice().toString().replace(',', '.')));
        });

        builder.append("COMMIT;");
        return builder.toString();
    }

    private String generateOrders(List<Order> orders) {
        var builder = new StringBuilder("BEGIN;\n");

        orders.forEach(order -> {
            builder.append("INSERT INTO orders.order(order_id,user_id)\n");
            builder.append(String.format("VALUES(UUID '%s',UUID '%s');\n",
                    order.getOrderId(),
                    order.getUserId()));
        });

        builder.append("COMMIT;");
        return builder.toString();
    }

    private String generateOrderDetails(List<OrderDetails> orderDetails) {
        var builder = new StringBuilder("BEGIN;\n");

        orderDetails.forEach(orderDetail -> {
            builder.append("INSERT INTO orders.order_details(detail_id,order_id,good_id,good_count)\n");
            builder.append(String.format("VALUES(UUID '%s',UUID '%s',UUID '%s',%d);\n",
                    orderDetail.getDetailId(),
                    orderDetail.getOrderId(),
                    orderDetail.getGoodId(),
                    orderDetail.getGoodsCount()));
        });

        builder.append("COMMIT;");
        return builder.toString();
    }

    private void writeToFile(String filePath, String content) throws IOException {
        var file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        var fileWriter = new FileWriter(file, StandardCharsets.UTF_8);
        fileWriter.write(content);
        fileWriter.close();
    }
}
