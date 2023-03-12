package dg.nisum.api.user.domain;

import dg.nisum.api.shared.domain.RequiredStringValueObject;

public class PhoneNumber extends RequiredStringValueObject {
    public PhoneNumber(String value) {
        super(value);
    }

    protected String requiredMessage(){
        return "required phone number";
    }
}
