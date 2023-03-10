package dg.nisum.users.user.infrastructure.http.register;

import dg.nisum.users.user.application.find.UserDto;
import dg.nisum.users.user.application.find.UserFinder;
import dg.nisum.users.user.application.register.PhoneRequest;
import dg.nisum.users.user.application.register.RegisterUserRequest;
import dg.nisum.users.user.application.register.UserRegister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class RegisterUserPostController {
    private final UserRegister register;
    private final UserFinder userFinder;

    public RegisterUserPostController(UserRegister register, UserFinder userFinder) {
        this.register = register;
        this.userFinder = userFinder;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public RestRegisterUserResponse register(@RequestBody RestRegisterUserRequest body) {
        String id = UUID.randomUUID().toString();
        String token = "2121212";
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
                }).collect(Collectors.toList()),
                token
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
