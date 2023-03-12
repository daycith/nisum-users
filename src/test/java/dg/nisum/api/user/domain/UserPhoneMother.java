package dg.nisum.api.user.domain;

public class UserPhoneMother {
    public static UserPhone create(PhoneNumber number, PhoneCityCode cityCode, PhoneCountryCode countryCode){
        return new UserPhone(number,cityCode,countryCode);
    }

    public static UserPhone random(){
        return create(
                PhoneNumberMother.random(),
                PhoneCityCodeMother.random(),
                PhoneCountryCodeMother.random()
        );
    }
}
