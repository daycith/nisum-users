package dg.nisum.api.user.domain;

import dg.nisum.api.shared.domain.RequiredStringValueObject;

public class UserEmail extends RequiredStringValueObject {

    private static final String REGULAR_EXPRESSION =  "^\\w+([-+.']\\w+)*@?(dominio.cl)$";

    public UserEmail(String value) {
        super(value);

        this.ensureValidEmail(value);
    }

    private void ensureValidEmail(String value){
        if(!value.matches(REGULAR_EXPRESSION)){
            throw new IllegalArgumentException("invalid email");
        }
    }

    protected String requiredMessage(){
        return "required email";
    }
}
