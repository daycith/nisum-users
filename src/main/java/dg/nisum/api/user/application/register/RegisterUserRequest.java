package dg.nisum.api.user.application.register;

import java.util.List;

public class RegisterUserRequest {
    private final String id;
    private final String name;
    private final String email;
    private final String password;

    private final List<PhoneRequest> phones;

    public RegisterUserRequest(String id, String name, String email, String password, List<PhoneRequest> phones) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
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

    public List<PhoneRequest> getPhones() {
        return phones;
    }
}


