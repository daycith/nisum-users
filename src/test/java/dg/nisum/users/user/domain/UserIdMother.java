package dg.nisum.users.user.domain;

import java.util.UUID;

public class UserIdMother {

    public static UserId random() {
        return new UserId(UUID.randomUUID().toString());
    }
}
