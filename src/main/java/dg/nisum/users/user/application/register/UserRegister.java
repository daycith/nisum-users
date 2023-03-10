package dg.nisum.users.user.application.register;

import dg.nisum.users.user.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserRegister {
    private final UserRepository repository;

    public UserRegister(UserRepository repository) {
        this.repository = repository;
    }

    public void register(RegisterUserRequest request){

        this.guardUserEmail(request.getEmail());

        UserId id = new UserId(request.getId());
        UserName name = new UserName(request.getName());
        UserEmail email = new UserEmail(request.getEmail());
        UserPassword password = new UserPassword(request.getPassword());

        List<UserPhone> phones = request.getPhones().stream().map(phone -> {
            return new UserPhone(
                    new PhoneNumber(phone.getNumber()),
                    new PhoneCityCode(phone.getCityCode()),
                    new PhoneCountryCode(phone.getCountryCode()));
        }).collect(Collectors.toList());

        var token = new UserToken(request.getToken());

        User user = User.create(id,name,email, password, phones,token);

        repository.save(user);
    }

    private void guardUserEmail(String email){
        Optional<User> optionalUser= repository.findByEmail(new UserEmail(email));

        if(optionalUser.isPresent()){
            throw  new UserEmailAlreadyExists("El correo ya registrado");
        }
    }
}
