package dg.nisum.users.shared.domain;

public interface PasswordEncrypter {
    String encrypt(String cleanValue);
}
