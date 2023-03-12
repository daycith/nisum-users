package dg.nisum.api.user.infrastructure.persistence.h2;

import dg.nisum.api.user.domain.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserEntity fromAggregate(User user){
        UserEntity entity = new UserEntity();

        String token  = user.getToken() != null ? user.getToken().value() : null;
        Date lastLoginDate = user.getLastLoginDate() != null ? user.getLastLoginDate().value() : null;

        List<PhoneEntity> phones = user.getPhones().stream().map(phone -> {
            return new PhoneEntity(phone.getNumber().value(),phone.getCityCode().value(),phone.getCountryCode().value());
        }).collect(Collectors.toList());

        entity.setId(user.getId().value());
        entity.setName(user.getName().value());
        entity.setEmail(user.getEmail().value());
        entity.setPhones(phones);
        entity.setPassword(user.getPassword().value());
        entity.setToken(token);
        entity.setCreated(user.getCreatedDate().value());
        entity.setModified(user.getUpdatedDate().value());
        entity.setLastLogin(lastLoginDate);
        entity.setIsActive(user.getIsActive().value());
        return entity;

    }


    public static User fromEntity(UserEntity userEntity){

        UserToken token = userEntity.getToken() != null ? new UserToken(userEntity.getToken()) : null;
        LastLoginDate lastLoginDate = userEntity.getLastLogin() != null ? new LastLoginDate(userEntity.getLastLogin()) : null;
        List<PhoneEntity> phones = userEntity.getPhones();

        return new User(
                new UserId(userEntity.getId()),
                new UserName(userEntity.getName()),
                new UserEmail(userEntity.getEmail()),
                new UserPassword(userEntity.getPassword()),
                phones.stream().map(phoneEntity -> {
                    return new UserPhone(
                            new PhoneNumber(phoneEntity.getNumber()),
                            new PhoneCityCode(phoneEntity.getCityCode()),
                            new PhoneCountryCode(phoneEntity.getCountryCode())
                    );
                }).collect(Collectors.toList()),
                new CreatedDate(userEntity.getCreated()),
                token,
                lastLoginDate,
                new UserIsActive(userEntity.getIsActive()),
                new UpdatedDate(userEntity.getModified())
        );
    }
}
