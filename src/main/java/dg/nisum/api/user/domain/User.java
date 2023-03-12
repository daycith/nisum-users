package dg.nisum.api.user.domain;

import dg.nisum.api.shared.domain.AggregateRoot;
import dg.nisum.api.user.domain.events.UserRegisteredDomainEvent;

import java.util.List;
import java.util.Objects;

public class User extends AggregateRoot {
    private UserId id;
    private UserName name;
    private UserEmail email;
    private UserPassword password;
    private List<UserPhone> phones;
    private UserToken token;

    private CreatedDate createdDate;
    private UpdatedDate updatedDate;
    private LastLoginDate lastLoginDate;
    private UserIsActive isActive;

    public User(
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
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
        this.createdDate = createdDate;
        this.token = token;
        this.lastLoginDate = lastLoginDate;
        this.isActive = isActive;
        this.updatedDate = updatedDate;
    }

    public void authenticate(UserToken token) {

        if (this.hasNeverAuthenticated()) {
            this.lastLoginDate = new LastLoginDate(this.createdDate.value());
        } else {
            this.lastLoginDate = LastLoginDate.current();
        }

        this.token = token;
    }

    private boolean hasNeverAuthenticated() {
        return this.lastLoginDate == null;
    }

    public static User create(
            UserId id,
            UserName name,
            UserEmail email,
            UserPassword password,
            List<UserPhone> phones
    ) {
        CreatedDate createdDate = CreatedDate.current();
        UpdatedDate updatedDate = new UpdatedDate(createdDate.value());
        User user = new User(
                id,
                name,
                email,
                password,
                phones,
                createdDate,
                null,
                null,
                UserIsActive.initialValue(),
                updatedDate
        );

        var userRegisteredDomainEvent = new UserRegisteredDomainEvent(
                user.getId().value(),
                user.getName().value(),
                user.getEmail().value(),
                user.getPassword().value()
        );

        user.record(userRegisteredDomainEvent);

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

    public CreatedDate getCreatedDate() {
        return createdDate;
    }

    public UpdatedDate getUpdatedDate() {
        return updatedDate;
    }
}
