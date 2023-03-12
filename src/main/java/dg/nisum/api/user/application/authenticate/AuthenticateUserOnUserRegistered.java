package dg.nisum.api.user.application.authenticate;

import dg.nisum.api.shared.domain.Service;
import dg.nisum.api.user.domain.events.UserRegisteredDomainEvent;
import org.springframework.context.event.EventListener;

@Service
public class AuthenticateUserOnUserRegistered {
    private final UserAuthenticator authenticator;

    public AuthenticateUserOnUserRegistered(UserAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    @EventListener
    public void on(UserRegisteredDomainEvent event){
        authenticator.authenticate(event.getEmail(),event.getPassword());
    }
}
