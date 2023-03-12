package dg.nisum.api.user.infrastructure.http.register;

import dg.nisum.api.user.application.register.PhoneRequest;
import dg.nisum.api.user.application.register.RegisterUserRequest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterUserRequestMapper {
    public static RegisterUserRequest fromRestRequest(String id, RestRegisterUserRequest restRequest){
        RegisterUserRequest request= new RegisterUserRequest(
                id,
                restRequest.getName(),
                restRequest.getEmail(),
                restRequest.getPassword(),
                fromPhoneRequests(restRequest.getPhones())
        );

        return request;
    }

    private static List<PhoneRequest> fromPhoneRequests(List<RestPhone> phoneRequestList){

        if(phoneRequestList == null){
            return Collections.emptyList();
        }

       return phoneRequestList.stream().map(phoneRequest -> {
            return new PhoneRequest(
                    phoneRequest.getNumber(),
                    phoneRequest.getCityCode(),
                    phoneRequest.getCountryCode()
            );
        }).collect(Collectors.toList());
    }
}
