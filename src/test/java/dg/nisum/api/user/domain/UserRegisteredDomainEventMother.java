package dg.nisum.api.user.domain;

public class UserRegisteredDomainEventMother {
    public static UserRegisteredDomainEvent create(
            String id,
            String name,
            String email,
            String password
    ){
        return new UserRegisteredDomainEvent(id,name,email,password);
    }

    public static UserRegisteredDomainEvent fromUser(User user){
        return create(
                user.getId().value(),
                user.getName().value(),
                user.getEmail().value(),
                user.getPassword().value()
        );
    }
}
