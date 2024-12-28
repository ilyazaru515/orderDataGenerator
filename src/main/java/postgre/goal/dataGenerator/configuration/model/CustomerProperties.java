package postgre.goal.dataGenerator.configuration.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "data.customer")
public class CustomerProperties {
    private Integer count;
}
