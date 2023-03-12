package dg.nisum.api.user.infrastructure.http.register;

import dg.nisum.api.user.domain.UserEmailMother;
import dg.nisum.api.user.domain.UserNameMother;
import dg.nisum.api.user.domain.UserPasswordMother;

import java.util.Collections;

public class RestRegisterUserRequestMother {

    public static RestRegisterUserRequest withEmail(String email) {
        return new RestRegisterUserRequest(
                UserNameMother.random().value(),
                UserEmailMother.create(email).value(),
                UserPasswordMother.random().value(),
                Collections.singletonList(PhoneRequestMother.random())
        );
    }
}
