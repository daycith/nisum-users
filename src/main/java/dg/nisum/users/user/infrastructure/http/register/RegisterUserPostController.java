package dg.nisum.users.user.infrastructure.http.register;

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

    public RegisterUserPostController(UserRegister register) {
        this.register = register;
    }

    @PostMapping(value = "/register",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody RestRegisterUserRequest body){
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

        return id;
    }
}
