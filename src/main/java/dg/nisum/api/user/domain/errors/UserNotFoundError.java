package dg.nisum.api.user.domain.errors;

import dg.nisum.api.shared.domain.DomainError;

public class UserNotFoundError extends DomainError {
    public UserNotFoundError() {
        super("user not found");
    }
}
