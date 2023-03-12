package dg.nisum.users.user.application.register;

import dg.nisum.users.shared.domain.EventBus;
import dg.nisum.users.shared.domain.PasswordEncrypter;
import dg.nisum.users.shared.domain.Service;
import dg.nisum.users.user.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserRegister {
    private final UserRepository repository;
    private final PasswordEncrypter passwordEncrypter;
    private final EventBus eventBus;

    private final LadaKeyPatternConfiguration ladaKeyPatternConfiguration;

    public UserRegister(UserRepository repository, PasswordEncrypter passwordEncrypter, LadaKeyPatternConfiguration ladaKeyPatternConfiguration, EventBus eventBus) {
        this.repository = repository;
        this.passwordEncrypter = passwordEncrypter;
        this.ladaKeyPatternConfiguration = ladaKeyPatternConfiguration;
        this.eventBus = eventBus;
    }

    public void register(RegisterUserRequest request) {

        this.guardUserEmail(request.getEmail());

        User user = makeUSer(request);

        repository.save(user);

        eventBus.publish(user.pullDomainEvents());
    }

    private void guardUserEmail(String email) {
        Optional<User> optionalUser = repository.findByEmail(new UserEmail(email));

        if (optionalUser.isPresent()) {
            throw new UserEmailAlreadyExists("El correo ya registrado");
        }
    }

    private User makeUSer(RegisterUserRequest request){
        UserId id = new UserId(request.getId());
        UserName name = new UserName(request.getName());
        UserEmail email = new UserEmail(request.getEmail());
        UserPassword password = new UserPassword(passwordEncrypter.encrypt(request.getPassword()));

        List<UserPhone> phones = makePhones(request.getPhones());

        return User.create(id, name, email, password, phones);

    }

    private List<UserPhone> makePhones(List<PhoneRequest> phones){

        LadaKeySpecification ladaKeySpecification = new LadaKeySpecification(ladaKeyPatternConfiguration.pattern());

        return phones.stream().map(phone -> {

            if(!ladaKeySpecification.isSatisfied(phone.getCountryCode())){
                throw new IllegalArgumentException("invalid lada key");
            }

            return new UserPhone(
                    new PhoneNumber(phone.getNumber()),
                    new PhoneCityCode(phone.getCityCode()),
                    new PhoneCountryCode(phone.getCountryCode()));
        }).collect(Collectors.toList());
    }

}
