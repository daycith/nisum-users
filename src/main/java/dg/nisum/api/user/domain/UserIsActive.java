package dg.nisum.api.user.domain;

public class UserIsActive {
    private final boolean value;

    public UserIsActive(boolean value) {
        this.value = value;
    }

    public boolean value() {
        return value;
    }

    static UserIsActive initialValue() {
        return new UserIsActive(true);
    }
}
