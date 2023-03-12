package dg.nisum.api.shared.domain;

public interface PasswordEncrypter {
    String encrypt(String cleanValue);
}
