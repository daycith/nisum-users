package dg.nisum.users.user.infrastructure.persistence.h2;

import dg.nisum.users.user.domain.*;

import java.util.Collections;
import java.util.Date;

public class UserMapper {

    public static UserEntity fromAggregate(User user){
        UserEntity entity = new UserEntity();

        String token  = user.getToken() != null ? user.getToken().value() : null;
        Date lastLoginDate = user.getLastLoginDate() != null ? user.getLastLoginDate().value() : null;

        entity.setId(user.getId().value());
        entity.setName(user.getName().value());
        entity.setEmail(user.getEmail().value());
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

        return new User(
                new UserId(userEntity.getId()),
                new UserName(userEntity.getName()),
                new UserEmail(userEntity.getEmail()),
                new UserPassword(userEntity.getPassword()),
                Collections.emptyList(),
                new CreatedDate(userEntity.getCreated()),
                token,
                lastLoginDate,
                new UserIsActive(userEntity.getIsActive()),
                new UpdatedDate(userEntity.getModified())
        );
    }
}
