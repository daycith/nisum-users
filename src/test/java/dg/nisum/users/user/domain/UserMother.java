package dg.nisum.users.user.domain;

import dg.nisum.users.user.application.register.RegisterUserRequest;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserMother {

    public static User create(
            UserId id,
            UserName name,
            UserEmail email,
            UserPassword password,
            List<UserPhone> phones,
            CreatedDate createdDate,
            UserToken token,
            LastLoginDate lastLoginDate,
            UserIsActive isActive,
            UpdatedDate updatedDate
    ){
        return new User(
                id,
                name,
                email,
                password,
                phones,
                createdDate,
                token,
                lastLoginDate,
                isActive,
                updatedDate
        );
    }

    public static User withEmail(String anEmail) {
        var id = UserIdMother.random();
        var name = UserNameMother.random();
        var email = UserEmailMother.create(anEmail);
        var password = UserPasswordMother.random();

        return create(
                id,
                name,
                email,
                password,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static User fromRequest(RegisterUserRequest request) {
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

        return create(
                id,
                name,
                email,
                password,
                phones,
                null,
                null,
                new LastLoginDate(new Date()),
                new UserIsActive(false),
                null
        );
    }

    public static User random(){
        return create(
                UserIdMother.random(),
                UserNameMother.random(),
                UserEmailMother.random(),
                UserPasswordMother.random(),
                Collections.singletonList(UserPhoneMother.random()),
                CreatedDateMother.random(),
                UserTokenMother.random(),
                LastLoginDateMother.random(),
                UserIsActiveMother.random(),
                UpdatedDateMother.random()
                );
    }
}
