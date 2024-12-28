package postgre.goal.model;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderDetails {
    private UUID detailId;
    private UUID orderId;
    private UUID goodId;
    private Integer goodsCount;
}
