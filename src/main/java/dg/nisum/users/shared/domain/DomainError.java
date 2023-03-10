package dg.nisum.users.shared.domain;

public abstract class DomainError extends RuntimeException {
    protected DomainError(String message) {
        super(message);
    }
}
