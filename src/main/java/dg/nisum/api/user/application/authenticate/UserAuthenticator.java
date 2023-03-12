package dg.nisum.api.user.application.authenticate;

import dg.nisum.api.shared.domain.ForbiddenError;
import dg.nisum.api.shared.domain.PasswordEncrypter;
import dg.nisum.api.shared.domain.Service;
import dg.nisum.api.user.domain.*;

@Service
public class UserAuthenticator {

    private final UserRepository repository;
    private final PasswordEncrypter passwordEncrypter;
    private final TokenProvider tokenProvider;
    public UserAuthenticator(UserRepository repository, PasswordEncrypter passwordEncrypter, TokenProvider tokenProvider) {
        this.repository = repository;
        this.passwordEncrypter = passwordEncrypter;
        this.tokenProvider  = tokenProvider;
    }

    void authenticate(String email, String cleanPassword){

        User user = repository.findByEmail(new UserEmail(email)).orElseThrow(() -> new ForbiddenError());

        String encryptedPassword = passwordEncrypter.encrypt(cleanPassword);

        if(!this.comparePasswords(user.getPassword().value(),encryptedPassword)){
            throw  new ForbiddenError();
        }

        String token = tokenProvider.generate(user);

        user.authenticate(new UserToken(token));

        repository.save(user);

    }

    private boolean comparePasswords(String userPassword, String providedPassword){
        return userPassword.equals(providedPassword);
    }
}
