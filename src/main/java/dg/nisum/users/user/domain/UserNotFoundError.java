package dg.nisum.users.user.domain;

import dg.nisum.users.shared.domain.DomainError;

public class UserNotFoundError extends DomainError {
    public UserNotFoundError(String id) {
        super("user not found");
    }
}
