package dg.nisum.users.steps;

import dg.nisum.users.user.infrastructure.http.register.RestRegisterUserRequest;
import io.cucumber.spring.ScenarioScope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope(proxyMode = ScopedProxyMode.NO)
public class RegisterUserInformation {

    private RestRegisterUserRequest request;

    public RestRegisterUserRequest getRequest() {
        return request;
    }

    public void setRequest(RestRegisterUserRequest request) {
        this.request = request;
    }

    private int status;
    private String response;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
