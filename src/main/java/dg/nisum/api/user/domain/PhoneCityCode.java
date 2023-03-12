package dg.nisum.api.user.domain;

import dg.nisum.api.shared.domain.RequiredStringValueObject;

public class PhoneCityCode extends RequiredStringValueObject {
    public PhoneCityCode(String value) {
        super(value);
    }

    protected String requiredMessage(){
        return "required city code";
    }
}
