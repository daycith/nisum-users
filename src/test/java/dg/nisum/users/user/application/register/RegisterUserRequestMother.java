package dg.nisum.users.user.application.register;


import dg.nisum.users.user.domain.UserEmailMother;
import dg.nisum.users.user.domain.UserNameMother;
import dg.nisum.users.user.domain.UserPasswordMother;

import java.util.Collections;
import java.util.UUID;

public class RegisterUserRequestMother {

    public static RegisterUserRequest random() {
        return new RegisterUserRequest(
                UUID.randomUUID().toString(),
                UserNameMother.random().value(),
                UserEmailMother.validRandom().value(),
                UserPasswordMother.random().value(),
                Collections.singletonList(
                        new PhoneRequest("1234567", "1", "57"))
        );
    }

    public static RegisterUserRequest withExternalEmail() {
        return new RegisterUserRequest(
                UUID.randomUUID().toString(),
                UserNameMother.random().value(),
                UserEmailMother.externalEmail(),
                UserPasswordMother.random().value(),
                Collections.singletonList(
                        new PhoneRequest("1234567", "1", "57"))
        );
    }
}
