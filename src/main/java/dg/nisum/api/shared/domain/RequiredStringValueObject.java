package dg.nisum.api.shared.domain;

public class RequiredStringValueObject extends StringValueObject {
    public RequiredStringValueObject(String value) {
        super(value);
        ensureIsValid(value);
    }

    private void ensureIsValid(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(requiredMessage());
        }
    }

    protected String requiredMessage(){
        return "required value";
    }
}
