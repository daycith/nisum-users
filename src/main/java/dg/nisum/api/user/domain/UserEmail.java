package dg.nisum.api.user.domain;

import dg.nisum.api.shared.domain.RequiredStringValueObject;

import java.util.regex.Pattern;

public class UserEmail extends RequiredStringValueObject {

    private static final String REGULAR_EXPRESSION =  "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@?(dominio.cl)$";

    public UserEmail(String value) {
        super(value);

        this.ensureValidEmail(value);
    }

    private void ensureValidEmail(String value){
        boolean matches = Pattern.compile(REGULAR_EXPRESSION, Pattern.CASE_INSENSITIVE).matcher(value).matches();
        if(!matches){
            throw new IllegalArgumentException("invalid email");
        }
    }

    protected String requiredMessage(){
        return "required email";
    }
}
