package dg.nisum.users.user.domain;

import com.github.javafaker.Faker;

public class PhoneNumberMother {
    public static PhoneNumber create(String value) {
        return new PhoneNumber(value);
    }

    public static PhoneNumber random() {
        return create(new Faker().phoneNumber().phoneNumber());
    }
}
