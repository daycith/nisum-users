package dg.nisum.users.user.infrastructure.http.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Phone")
public class RestPhone {
    @NotBlank
    @Schema(example = "1234567")
    private String number;

    @NotBlank
    @Schema(example = "1")
    @JsonProperty("citycode")
    private String cityCode;

    @NotBlank
    @Schema(example = "57")
    @JsonProperty("contrycode")
    private String countryCode;
}
