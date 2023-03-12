package dg.nisum.api.user.domain;

import dg.nisum.api.shared.domain.DomainEvent;

import java.util.Objects;

public class UserRegisteredDomainEvent implements DomainEvent {

    private static final  String QUALIFIED_NAME = "user.registered";

    private final String id;
    private final String name;
    private final String email;
    private final String password;
    public UserRegisteredDomainEvent(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String fullQualifiedEventName() {
        return QUALIFIED_NAME;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegisteredDomainEvent event = (UserRegisteredDomainEvent) o;
        return id.equals(event.id) && name.equals(event.name) && email.equals(event.email) && password.equals(event.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }
}
