package dg.nisum.api.user.domain;

import dg.nisum.api.user.domain.events.UserRegisteredDomainEvent;

public class UserRegisteredDomainEventMother {
    public static UserRegisteredDomainEvent create(
            String id,
            String name,
            String email,
            String password
    ){
        return new UserRegisteredDomainEvent(id,name,email,password);
    }

    public static UserRegisteredDomainEvent fromUserAndCleanPassword(User user, String cleanPassword){
        return create(
                user.getId().value(),
                user.getName().value(),
                user.getEmail().value(),
                cleanPassword
        );
    }
}
