package postgre.goal.dataGenerator.configuration.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "data.good")
public class GoodProperties {
    private Integer count;
    private Double minPrice;
    private Double maxPrice;
}
