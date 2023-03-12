package dg.nisum.api.user.domain;

import com.github.javafaker.Faker;

public class UserTokenMother {

    public static UserToken create(String value) {
        return new UserToken(value);
    }

    public static UserToken random() {
        return create(new Faker().lorem().word());
    }
}
