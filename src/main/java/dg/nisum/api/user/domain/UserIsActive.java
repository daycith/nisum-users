package dg.nisum.api.user.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIsActive that = (UserIsActive) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
