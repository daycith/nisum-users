package dg.nisum.api.user.application.find;

public class PhoneDto {
    private final String number;
    private final String cityCode;
    private final String countryCode;

    public PhoneDto(String number, String cityCode, String countryCode) {
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
