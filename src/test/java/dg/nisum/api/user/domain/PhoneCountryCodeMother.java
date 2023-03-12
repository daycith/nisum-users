package dg.nisum.api.user.domain;

import com.github.javafaker.Faker;


public class PhoneCountryCodeMother {
    public static PhoneCountryCode create(String value) {
        return new PhoneCountryCode(value);
    }

    public static PhoneCountryCode random() {
        return create(new Faker().phoneNumber().subscriberNumber(2));
    }
}
