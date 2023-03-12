package dg.nisum.api.user.application.register;


import dg.nisum.api.user.domain.UserEmailMother;
import dg.nisum.api.user.domain.UserNameMother;
import dg.nisum.api.user.domain.UserPasswordMother;

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

    public static RegisterUserRequest withCountryCode(String countryCode) {
        return new RegisterUserRequest(
                UUID.randomUUID().toString(),
                UserNameMother.random().value(),
                UserEmailMother.validRandom().value(),
                UserPasswordMother.random().value(),
                Collections.singletonList(
                        new PhoneRequest("1234567", "1", countryCode))
        );
    }
}
