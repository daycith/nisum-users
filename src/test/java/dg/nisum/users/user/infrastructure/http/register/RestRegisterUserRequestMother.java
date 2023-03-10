package dg.nisum.users.user.infrastructure.http.register;

import dg.nisum.users.user.application.register.PhoneRequest;
import dg.nisum.users.user.domain.UserEmailMother;
import dg.nisum.users.user.domain.UserNameMother;
import dg.nisum.users.user.domain.UserPassword;
import dg.nisum.users.user.domain.UserPasswordMother;

import java.util.Collections;

public class RestRegisterUserRequestMother {

    public static RestRegisterUserRequest withEmail(String email){
        return new RestRegisterUserRequest(
                UserNameMother.random().value(),
                UserEmailMother.create(email).value(),
                UserPasswordMother.random().value(),
                Collections.singletonList(PhoneRequestMother.random())
        );
    }
}
