package dg.nisum.users.user.domain;

import com.github.javafaker.Faker;

import java.util.Date;

public class LastLoginDateMother {
    public static LastLoginDate create(Date date){
        return new LastLoginDate(date);
    }

    public static LastLoginDate random(){
        return  create(new Date());
    }
}
