package dg.nisum.api.shared.domain;

public class ForbiddenError extends RuntimeException {
    public ForbiddenError() {
        super("Forbidden");
    }
}
