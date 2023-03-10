package dg.nisum.users.user.domain;

import com.github.javafaker.Faker;

public class UserPasswordMother {

    public static UserPassword create(String value) {
        return new UserPassword(value);
    }

    public static UserPassword random() {
        return create(new Faker().internet().password());
    }
}
