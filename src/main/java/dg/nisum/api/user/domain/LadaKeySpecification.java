package dg.nisum.api.user.domain;

public class LadaKeySpecification {
    private final String pattern;

    public LadaKeySpecification(String pattern){
        this.pattern = pattern;
    }
//
    public boolean isSatisfied(String key){
        return key.matches(pattern);
    }
}
