package dg.nisum.users.user.domain;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {
    private UserId id;

    private UserName name;
    private UserEmail email;

    private UserPassword password;

    private List<UserPhone> phones;

    private UserToken token;

    private LastLoginDate lastLoginDate;

    private UserIsActive isActive;


    public User(
            UserId id,
            UserName name,
            UserEmail email,
            UserPassword password,
            List<UserPhone> phones,
            UserToken token,
            LastLoginDate lastLoginDate,
            UserIsActive isActive
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
        this.token = token;
        this.lastLoginDate = lastLoginDate;
        this.isActive = isActive;
    }

    public static User create(
            UserId id,
            UserName name,
            UserEmail email,
            UserPassword password,
            List<UserPhone> phones,
            UserToken token
    ) {
        var user = new User(
                id,
                name,
                email,
                password,
                phones,
                token,
                new LastLoginDate(new Date()),
                UserIsActive.initialValue()

        );

        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
//                && name.equals(user.name) && email.equals(user.email) && password.equals(user.password) && phones.equals(user.phones) && token.equals(user.token) && isActive.equals(user.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, phones, token, isActive);
    }


    public UserId getId() {
        return id;
    }

    public UserName getName() {
        return name;
    }

    public UserEmail getEmail() {
        return email;
    }

    public UserPassword getPassword() {
        return password;
    }

    public List<UserPhone> getPhones() {
        return phones;
    }

    public UserToken getToken() {
        return token;
    }

    public LastLoginDate getLastLoginDate() {
        return lastLoginDate;
    }

    public UserIsActive getIsActive() {
        return isActive;
    }
}
