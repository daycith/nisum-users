package dg.nisum.users.user.infrastructure.http.register;

import dg.nisum.users.user.domain.PhoneCityCodeMother;
import dg.nisum.users.user.domain.PhoneCountryCodeMother;
import dg.nisum.users.user.domain.PhoneNumberMother;

public class PhoneRequestMother {


    public static RestPhone create(String number, String cityCode, String countryCode) {
        return new RestPhone(number, cityCode, countryCode);
    }

    public static RestPhone random() {
        return create(
                PhoneNumberMother.random().value(),
                PhoneCityCodeMother.random().value(),
                PhoneCountryCodeMother.random().value()
        );
    }
}
