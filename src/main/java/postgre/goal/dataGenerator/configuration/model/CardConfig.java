package postgre.goal.dataGenerator.configuration.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "data.card")
public class CardConfig {
    private Integer maxCardCount;
    private Double minAmount;
    private Double maxAmount;
}
