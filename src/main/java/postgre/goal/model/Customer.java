package postgre.goal.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Customer {
    private UUID userId;
    private String userName;
    private String password;
    private String email;
}
