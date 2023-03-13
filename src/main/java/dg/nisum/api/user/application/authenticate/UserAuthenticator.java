package dg.nisum.api.user.application.authenticate;

import dg.nisum.api.shared.domain.ForbiddenError;
import dg.nisum.api.shared.domain.PasswordComparator;
import dg.nisum.api.shared.domain.PasswordEncrypter;
import dg.nisum.api.shared.domain.Service;
import dg.nisum.api.user.domain.*;

@Service
public class UserAuthenticator {

    private final UserRepository repository;
    private final PasswordComparator passwordComparator;
    private final TokenProvider tokenProvider;

    public UserAuthenticator(UserRepository repository, PasswordComparator passwordComparator, TokenProvider tokenProvider) {
        this.repository = repository;
        this.passwordComparator = passwordComparator;
        this.tokenProvider = tokenProvider;
    }

    void authenticate(String email, String cleanPassword) {

        User user = repository.findByEmail(new UserEmail(email)).orElseThrow(() -> new ForbiddenError());

        this.comparePasswords(cleanPassword, user.getPassword().value());

        String token = tokenProvider.generate(user);

        user.authenticate(new UserToken(token));

        repository.save(user);

    }

    private void comparePasswords(String cleanPassword, String userPassword) {
        if (!passwordComparator.compare(cleanPassword, userPassword)) {
            throw new ForbiddenError();
        }
    }
}
