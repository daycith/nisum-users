package dg.nisum.users.user.domain;

import dg.nisum.users.shared.domain.DomainError;

public class UserEmailAlreadyExists extends DomainError {
    public UserEmailAlreadyExists(String message) {
        super(message);
    }
}
