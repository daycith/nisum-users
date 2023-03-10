package dg.nisum.users.user.application.register;


import java.util.Collections;
import java.util.UUID;

public class RegisterUserRequestMother {

    public static RegisterUserRequest random() {
        return new RegisterUserRequest(
                UUID.randomUUID().toString(),
                "Juan Rodriguez",
                "juan@rodriguez.com",
                "aaa",
                Collections.singletonList(
                        new PhoneRequest("1234567", "1", "57")),
                "abcdfgf"
        );
    }
}
