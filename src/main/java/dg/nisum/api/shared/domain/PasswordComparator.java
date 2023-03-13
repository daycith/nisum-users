package dg.nisum.api.shared.domain;

public interface PasswordComparator {
    boolean compare(String plainPassword, String userPassword);
}
