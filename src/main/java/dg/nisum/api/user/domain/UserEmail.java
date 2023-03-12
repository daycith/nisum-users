package dg.nisum.api.user.domain;

import dg.nisum.api.shared.domain.StringValueObject;

public class UserEmail extends StringValueObject {

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
}