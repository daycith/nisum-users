package dg.nisum.users.user.infrastructure.http.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestPhone {
    private String number;

    @JsonProperty("citycode")
    private String cityCode;
    @JsonProperty("contrycode")
    private String countryCode;
}
