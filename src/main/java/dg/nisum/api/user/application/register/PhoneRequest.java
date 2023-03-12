package dg.nisum.api.user.application.register;

public class PhoneRequest {
    private final String number;
    private final String cityCode;
    private final String countryCode;

    public PhoneRequest(String number, String cityCode, String countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
