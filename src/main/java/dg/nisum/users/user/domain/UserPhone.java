package dg.nisum.users.user.domain;

public class UserPhone {
    private final PhoneNumber number;
    private final PhoneCityCode cityCode;
    private final PhoneCountryCode countryCode;


    public UserPhone(PhoneNumber number, PhoneCityCode cityCode, PhoneCountryCode countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }


}
