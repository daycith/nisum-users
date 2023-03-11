package dg.nisum.users.user.domain;

import com.github.javafaker.Faker;

public class UserEmailMother {

    public static UserEmail create(String value) {
        return new UserEmail(value);
    }

    public static UserEmail validRandom() {
        return create(new Faker().name().username()+"@dominio.cl");
    }

    public static String externalEmail(){
        return new Faker().internet().emailAddress();
    }
}
