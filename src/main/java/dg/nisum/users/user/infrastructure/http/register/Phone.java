package dg.nisum.users.user.infrastructure.http.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Phone {
    private final String number;

    @JsonProperty("citycode")
    private final String cityCode;
    @JsonProperty("contrycode")
    private final String countryCode;
}
