package dg.nisum.api.user.application.register;

import dg.nisum.api.shared.domain.PasswordEncrypter;
import dg.nisum.api.shared.domain.Service;
import dg.nisum.api.shared.domain.events.EventBus;
import dg.nisum.api.user.domain.*;
import dg.nisum.api.user.domain.errors.UserEmailAlreadyExists;

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

        User user = userFactory(request);

        repository.save(user);

        eventBus.publish(user.pullDomainEvents());
    }

    private void guardUserEmail(String email) {
        Optional<User> optionalUser = repository.findByEmail(new UserEmail(email));

        if (optionalUser.isPresent()) {
            throw new UserEmailAlreadyExists("El correo ya registrado");
        }
    }

    private User userFactory(RegisterUserRequest request) {
        UserId id = new UserId(request.getId());
        UserName name = new UserName(request.getName());
        UserEmail email = new UserEmail(request.getEmail());
        UserPassword password = new UserPassword(passwordEncrypter.encrypt(request.getPassword()));

        List<UserPhone> phones = makePhones(request.getPhones());

        return User.create(id, name, email, password, phones);

    }

    private List<UserPhone> makePhones(List<PhoneRequest> phones) {
        LadaKeySpecification ladaKeySpecification = new LadaKeySpecification(ladaKeyPatternConfiguration.pattern());
        return phones.stream().map(phone -> {
            return mapPhone(phone, ladaKeySpecification);
        }).collect(Collectors.toList());
    }

    private UserPhone mapPhone(PhoneRequest phoneRequest, LadaKeySpecification ladaKeySpecification) {

        if (!ladaKeySpecification.isSatisfied(phoneRequest.getCountryCode())) {
            throw new IllegalArgumentException("invalid lada key");
        }

        return new UserPhone(
                new PhoneNumber(phoneRequest.getNumber()),
                new PhoneCityCode(phoneRequest.getCityCode()),
                new PhoneCountryCode(phoneRequest.getCountryCode()));
    }

}
