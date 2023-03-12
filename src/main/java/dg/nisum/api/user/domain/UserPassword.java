package dg.nisum.api.user.domain;

import dg.nisum.api.shared.domain.RequiredStringValueObject;

public class UserPassword extends RequiredStringValueObject {
    public UserPassword(String value) {
        super(value);
    }

    protected String requiredMessage(){
        return "required password";
    }
}
