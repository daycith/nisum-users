package dg.nisum.api.user.domain;

import dg.nisum.api.shared.domain.RequiredStringValueObject;

public class PhoneCountryCode extends RequiredStringValueObject {
    public PhoneCountryCode(String value) {
        super(value);
    }

    protected String requiredMessage(){
        return "required country code";
    }
}
