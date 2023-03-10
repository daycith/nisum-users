package dg.nisum.users.user.application.find;

import dg.nisum.users.user.domain.User;

import java.util.stream.Collectors;

public class UserDtoMapper {
    protected static UserDto fromAggregate(User user) {
        return new UserDto(
                user.getId().value(),
                user.getName().value(),
                user.getEmail().value(),
                user.getPhones().stream().map(phone -> {
                    return new PhoneDto(
                            phone.getNumber().value(),
                            phone.getCityCode().value(),
                            phone.getCountryCode().value()
                    );
                }).collect(Collectors.toList()),
                null,
                null,
                null,
                user.getToken().value(),
                user.getIsActive().value()
        );
    }
}
