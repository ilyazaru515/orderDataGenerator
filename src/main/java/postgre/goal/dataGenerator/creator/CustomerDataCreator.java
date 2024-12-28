package postgre.goal.dataGenerator.creator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import postgre.goal.dataGenerator.configuration.model.CustomerProperties;
import postgre.goal.dataGenerator.data.LinesSaver;
import postgre.goal.dataGenerator.util.Randomizer;
import postgre.goal.model.Customer;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomerDataCreator implements DataCreator<Customer> {
    private final CustomerProperties customerProperties;
    private final LinesSaver linesSaver;
    private final Randomizer randomizer;

    private Customer createCustomer() {
        var gender = randomizer.randomBoolean() ? Gender.MALE : Gender.FEMALE;
        var customer = new Customer();
        customer.setUserId(UUID.randomUUID());
        customer.setUserName(getName(gender));
        customer.setPassword(randomizer.randomString(17));
        customer.setEmail(randomizer.randomString(20) + "@mail.ru");
        return customer;
    }

    private String getName(Gender gender) {
        if (Gender.MALE.equals(gender)) {
            return linesSaver.getMansSurnames().get(randomizer.randomInt(linesSaver.getMansSurnames().size())) + " " +
                    linesSaver.getMansNames().get(randomizer.randomInt(linesSaver.getMansSurnames().size())) + " " +
                    linesSaver.getMansPatronymics().get(randomizer.randomInt(linesSaver.getMansPatronymics().size()));
        } else {
            return linesSaver.getWomenSurnames().get(randomizer.randomInt(linesSaver.getWomenSurnames().size())) + " " +
                    linesSaver.getWomenNames().get(randomizer.randomInt(linesSaver.getWomenNames().size())) + " " +
                    linesSaver.getWomenPatronymics().get(randomizer.randomInt(linesSaver.getWomenPatronymics().size()));
        }
    }

    @Override
    public List<Customer> createDataBatch() {
        var uniqueCustomers = new HashMap<String, Customer>();
        for (int i = 0; i < customerProperties.getCount(); i++) {
            var customer = createCustomer();
            if (uniqueCustomers.containsKey(customer.getUserName())) {
                customer.setUserName(customer.getUserName() + randomizer.randomInt(customerProperties.getCount()));
            }
            uniqueCustomers.put(customer.getUserName(), customer);
        }
        return new ArrayList<>(uniqueCustomers.values());
    }

    enum Gender {
        MALE, FEMALE;
    }
}
