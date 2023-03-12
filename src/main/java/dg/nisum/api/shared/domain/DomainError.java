package dg.nisum.api.shared.domain;

public abstract class DomainError extends RuntimeException {
    protected DomainError(String message) {
        super(message);
    }
}
