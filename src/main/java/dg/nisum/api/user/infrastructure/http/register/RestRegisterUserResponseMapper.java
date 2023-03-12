package dg.nisum.api.user.infrastructure.http.register;

import dg.nisum.api.user.application.find.UserDto;

import java.util.stream.Collectors;

public class RestRegisterUserResponseMapper {
    
    public static RestRegisterUserResponse fromDto(UserDto userDto){
        return new RestRegisterUserResponse(
                userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPhones().stream().map(phone -> {
                    return new RestPhone(
                            phone.getNumber(),
                            phone.getCityCode(),
                            phone.getCountryCode()
                    );
                }).collect(Collectors.toList()),
                userDto.getCreated(),
                userDto.getModified(),
                userDto.getLastLogin(),
                userDto.getToken(),
                userDto.isActive()
        );
    }
}
