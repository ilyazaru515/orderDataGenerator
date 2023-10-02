package postgre.goal.dataGenerator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import postgre.goal.dataGenerator.configuration.model.CustomerConfig;
import postgre.goal.dataGenerator.data.LinesSaver;
import postgre.goal.dataGenerator.model.Customer;

import java.util.*;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CustomerDataCreator implements DataCreator<Customer,Void> {
    private final CustomerConfig customerConfig;
    private final LinesSaver linesSaver;
    private final Random RANDOMIZER = new Random();

    private Customer createCustomer() {
        var gender = RANDOMIZER.nextBoolean() ? Gender.MALE : Gender.FEMALE;
        var customer = new Customer();
        customer.setUserId(UUID.randomUUID());
        customer.setUserName(getName(gender));
        customer.setUserPassword("password" + customer.getUserName());
        customer.setEmail(linesSaver.getMails().get(RANDOMIZER.nextInt(linesSaver.getMails().size())));
        return customer;
    }

    private String getName(Gender gender) {
        if (Gender.MALE.equals(gender)) {
            return linesSaver.getMansNames().get(RANDOMIZER.nextInt(linesSaver.getMansNames().size()));
        } else {
            return linesSaver.getWomansNames().get(RANDOMIZER.nextInt(linesSaver.getWomansNames().size()));
        }
    }

    @Override
    public List<Customer> createDataBatchWithForeignKeys(Supplier<Set<Void>> foreignKeysSupplier) {
        return createDataBatch();
    }

    @Override
    public List<Customer> createDataBatch() {
        var uniqueCustomers = new HashMap<String, Customer>();
        for (int i = 0; i < customerConfig.getCount(); i++) {
            var customer = createCustomer();
            if (uniqueCustomers.containsKey(customer.getUserName())) {
                customer.setUserName(customer.getUserName() + RANDOMIZER.nextInt(customerConfig.getCount()));
            }
            uniqueCustomers.put(customer.getUserName(), customer);
        }
        return new ArrayList<>(uniqueCustomers.values());
    }

    enum Gender {
        MALE, FEMALE;
    }
}
