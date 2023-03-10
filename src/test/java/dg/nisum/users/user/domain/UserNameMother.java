package dg.nisum.users.user.domain;

import com.github.javafaker.Faker;

public class UserNameMother {

    public static UserName create(String value) {
        return new UserName(value);
    }

    public static UserName random() {
        return create(new Faker().name().fullName());
    }
}
