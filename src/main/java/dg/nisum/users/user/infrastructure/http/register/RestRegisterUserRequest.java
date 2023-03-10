package dg.nisum.users.user.infrastructure.http.register;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RestRegisterUserRequest {
    private final String name;
    private final String email;
    private final String password;

    private final List<Phone> phones;
}
