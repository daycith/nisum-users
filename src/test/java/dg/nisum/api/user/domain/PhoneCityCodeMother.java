package dg.nisum.api.user.domain;

import com.github.javafaker.Faker;

public class PhoneCityCodeMother {
    public static PhoneCityCode create(String value) {
        return new PhoneCityCode(value);
    }

    public static PhoneCityCode random() {
        return create(new Faker().phoneNumber().subscriberNumber(2));
    }
}
