package dg.nisum.api.user.domain;

public interface TokenProvider {
    String generate(User user);
}
