package dg.nisum.users.user.domain;

public interface TokenProvider {
    String generate(User user);
}
