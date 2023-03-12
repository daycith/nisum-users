package dg.nisum.users.user.infrastructure;

import dg.nisum.users.user.domain.LadaKeyPatternConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component()
public class PropertiesLadaKeyConfiguration implements LadaKeyPatternConfiguration {
    @Value("${users.ladakey_pattern}")
    private String ladaKeyPattern;

    @Override
    public String pattern() {
        return ladaKeyPattern;
    }
}
