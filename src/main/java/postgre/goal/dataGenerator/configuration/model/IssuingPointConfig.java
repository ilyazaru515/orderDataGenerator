package postgre.goal.dataGenerator.configuration.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "data.issuing-point")
public class IssuingPointConfig {
    private Integer count;
}
