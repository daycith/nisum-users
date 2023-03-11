package dg.nisum.users.shared.domain;

public class ForbiddenError extends RuntimeException {
    public ForbiddenError() {
        super("Forbidden");
    }
}
