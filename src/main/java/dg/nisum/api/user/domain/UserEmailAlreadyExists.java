package dg.nisum.api.user.domain;

import dg.nisum.api.shared.domain.DomainError;

public class UserEmailAlreadyExists extends DomainError {
    public UserEmailAlreadyExists(String message) {
        super(message);
    }
}
