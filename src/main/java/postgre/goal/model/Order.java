package postgre.goal.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Order {
    private UUID orderId;
    private UUID userId;
}
