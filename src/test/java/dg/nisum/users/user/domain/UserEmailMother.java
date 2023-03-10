package dg.nisum.users.user.domain;

import com.github.javafaker.Faker;

public class UserEmailMother {

    public static UserEmail create(String value){
        return new UserEmail(value);
    }

    public static UserEmail random(){
        return create(new Faker().internet().emailAddress("dominio.cl"));
    }
}
