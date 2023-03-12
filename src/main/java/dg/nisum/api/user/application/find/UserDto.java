package dg.nisum.api.user.application.find;


import java.util.Date;
import java.util.List;

public class UserDto {
    private final String id;
    private final String name;
    private final String email;
    private final List<PhoneDto> phones;

    private final Date created;
    private final Date modified;

    private final Date lastLogin;

    private final String token;

    public UserDto(
            String id,
            String name,
            String email,
            List<PhoneDto> phones,
            Date created,
            Date modified,
            Date lastLogin,
            String token,
            boolean isActive
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phones = phones;
        this.created = created;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.token = token;
        this.isActive = isActive;
    }

    private final boolean isActive;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<PhoneDto> getPhones() {
        return phones;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public String getToken() {
        return token;
    }

    public boolean isActive() {
        return isActive;
    }
}
