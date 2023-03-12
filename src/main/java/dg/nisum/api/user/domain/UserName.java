package dg.nisum.api.user.domain;

import dg.nisum.api.shared.domain.RequiredStringValueObject;

public class UserName extends RequiredStringValueObject {
    public UserName(String value) {
        super(value);
    }

    protected String requiredMessage(){
        return "required name";
    }
}
