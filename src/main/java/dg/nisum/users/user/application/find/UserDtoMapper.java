package dg.nisum.users.user.application.find;

import dg.nisum.users.user.domain.LastLoginDate;
import dg.nisum.users.user.domain.UpdatedDate;
import dg.nisum.users.user.domain.User;
import dg.nisum.users.user.domain.UserToken;

import java.util.stream.Collectors;

public class UserDtoMapper {
    protected static UserDto fromAggregate(User user) {

        UserToken token = user.getToken();
        LastLoginDate lastLoginDate = user.getLastLoginDate();
        UpdatedDate updatedDate = user.getUpdatedDate();
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
                user.getCreatedDate().value(),
                updatedDate != null ? updatedDate.value() : null,
                lastLoginDate != null ? lastLoginDate.value() : null,
                 token != null ? token.value() : null,
                user.getIsActive().value()
        );
    }
}
