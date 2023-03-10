package dg.nisum.users.user.domain;

public class UserIsActiveMother {
    public static UserIsActive create(boolean value){
        return new UserIsActive(value);
    }

    public static  UserIsActive random(){
        return create(UserIsActive.initialValue().value());
    }
}
