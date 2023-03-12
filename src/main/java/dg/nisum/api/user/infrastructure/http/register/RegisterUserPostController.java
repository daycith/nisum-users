package dg.nisum.api.user.infrastructure.http.register;

import dg.nisum.api.user.application.find.UserDto;
import dg.nisum.api.user.application.find.UserFinder;
import dg.nisum.api.user.application.register.PhoneRequest;
import dg.nisum.api.user.application.register.RegisterUserRequest;
import dg.nisum.api.user.application.register.UserRegister;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@Tag(name = "Users")
public class RegisterUserPostController {
    private final UserRegister register;
    private final UserFinder userFinder;

    public RegisterUserPostController(UserRegister register, UserFinder userFinder) {
        this.register = register;
        this.userFinder = userFinder;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    @Operation(description = "Register User")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return the registered user info."),
            @ApiResponse(responseCode = "422",
                    description = "User cannot be registered. See the error details for more info",
                    content = @Content)
    })
    public RestRegisterUserResponse register(@RequestBody RestRegisterUserRequest body) {
        String id = UUID.randomUUID().toString();
        RegisterUserRequest request = new RegisterUserRequest(
                id,
                body.getName(),
                body.getEmail(),
                body.getPassword(),
                body.getPhones().stream().map(phoneRequest -> {
                    return new PhoneRequest(
                            phoneRequest.getNumber(),
                            phoneRequest.getCityCode(),
                            phoneRequest.getCountryCode()
                    );
                }).collect(Collectors.toList())
        );

        register.register(request);

        UserDto userDto = userFinder.find(id);

        return new RestRegisterUserResponse(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPhones().stream().map(phone -> {
                    return new RestPhone(
                            phone.getNumber(),
                            phone.getCityCode(),
                            phone.getCountryCode()
                    );
                }).collect(Collectors.toList()),
                userDto.getCreated(),
                userDto.getModified(),
                userDto.getLastLogin(),
                userDto.getToken(),
                userDto.isActive()
        );
    }
}
