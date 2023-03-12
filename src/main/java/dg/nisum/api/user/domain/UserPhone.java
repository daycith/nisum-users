package dg.nisum.api.user.domain;

public class UserPhone {
    private final PhoneNumber number;
    private final PhoneCityCode cityCode;
    private final PhoneCountryCode countryCode;


    public UserPhone(PhoneNumber number, PhoneCityCode cityCode, PhoneCountryCode countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public PhoneNumber getNumber() {
        return number;
    }

    public PhoneCityCode getCityCode() {
        return cityCode;
    }

    public PhoneCountryCode getCountryCode() {
        return countryCode;
    }
}
