package dg.nisum.users.user.domain;

import dg.nisum.users.user.application.register.RegisterUserRequest;

import java.util.Date;
import java.util.stream.Collectors;

public class UserMother {

    public static User withEmail(String anEmail){
        var id = UserIdMother.random();
        var name = UserNameMother.random();
        var email = UserEmailMother.create(anEmail);
        var password = UserPasswordMother.random();

        User user = new User(
                id,
                name,
                email,
                password,
                null,
                null,
                null,
                null
        );

        return user;
    }

    public static User fromRequest(RegisterUserRequest request){
        var id = new UserId(request.getId());
        var name = new UserName(request.getName());
        var email = new UserEmail(request.getEmail());
        var password = new UserPassword(request.getPassword());

        var phones = request.getPhones().stream().map(phone -> {
            return new UserPhone(
                    new PhoneNumber(phone.getNumber()),
                    new PhoneCityCode(phone.getCityCode()),
                    new PhoneCountryCode(phone.getCountryCode()));
        }).collect(Collectors.toList());

        var token = new UserToken(request.getToken());

        User user = new User(
                id,
                name,
                email,
                password,
                phones,
                token,
                new LastLoginDate(new Date()),
                new UserIsActive(false)
        );

        return user;
    }
}
