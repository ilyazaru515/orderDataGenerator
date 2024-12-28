package postgre.goal.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderInfo {
    private List<Order> orders;
    private List<OrderDetails> orderDetails;
}
