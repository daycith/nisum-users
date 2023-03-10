package dg.nisum.users.user.infrastructure.http.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestRegisterUserResponse {
    private String id;
    private String name;
    private String email;
    private List<RestPhone> phones;

    private Date created;
    private Date modified;
    @JsonProperty("last_login")
    private Date lastLogin;

    private String token;
    @JsonProperty("isactive")
    private boolean isActive;

}
