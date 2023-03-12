package dg.nisum.api.user.infrastructure.http.register;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "RegisterUserRequest")
public class RestRegisterUserRequest {
    @NotBlank
    @Schema(example = "Juan Rodriguez")
    private String name;
    @NotBlank
    @Schema(example = "juan@dominio.cl")
    private String email;
    @NotBlank
    @Schema(example = "hunter2")
    private String password;

    private List<RestPhone> phones;
}
