package dg.nisum.users.user.infrastructure.http.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestRegisterUserRequest {
    private String name;
    private String email;
    private String password;

    private List<RestPhone> phones;
}
