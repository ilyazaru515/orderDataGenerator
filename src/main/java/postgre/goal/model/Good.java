package postgre.goal.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Good {
    private UUID goodId;
    private String goodName;
    private Double goodPrice;
}
