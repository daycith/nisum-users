package dg.nisum.api.user.infrastructure.http.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestRegisterUserResponse {
    @Schema(example = "123e4567-e89b-12d3-a456-426614174000")
    private String id;

    @Schema(example = "Juan Rodriguez")
    private String name;
    @Schema(example = "juan@dominio.cl")
    private String email;
    private List<RestPhone> phones;

    private Date created;
    private Date modified;
    @JsonProperty("last_login")
    private Date lastLogin;

    @Schema(example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZjNlZjI5Ni02MGE2LTQ5MDgtYTY2ZC1jYTkxMjcwNTA1MWYiLCJleHAiOjE2Nzg2MzYyMTV9.iGmMc9la6PTrTQTUPTTvCnLvV2i1vD15B0mEt74JFeDx17rELHst5-sD6YiKtyeIeTjpm_6CYCLpnWM66MebvQ")
    private String token;
    @JsonProperty("isactive")
    private boolean isActive;

}
