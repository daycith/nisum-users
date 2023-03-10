package dg.nisum.users.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dg.nisum.users.user.domain.UserRepository;
import dg.nisum.users.user.infrastructure.http.register.RestRegisterUserRequest;
import dg.nisum.users.user.infrastructure.http.register.RestRegisterUserRequestMother;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSteps {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RegisterUserInformation registerUserInformation;

    @Autowired
    private UserRepository repository;
    @After
    public void arrange(){
        repository.deleteAll();
    }

    @When("I send a POST request to {string} with body:")
    public void i_send_a_post_request_to_with_body(String url, String body) throws JsonProcessingException {
        RestRegisterUserRequest request = new ObjectMapper().readValue(body, RestRegisterUserRequest.class);
        ResponseEntity<String> responseEntity = registerUser(url, request);

        registerUserInformation.setStatus(responseEntity.getStatusCodeValue());
        registerUserInformation.setResponse(responseEntity.getBody());
    }

    @Then("The response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        assertEquals(statusCode, registerUserInformation.getStatus());
    }

    @Given("There is a user with the email {string}")
    public void there_is_a_user_with_the_email(String email) {
        RestRegisterUserRequest request = RestRegisterUserRequestMother.withEmail(email);

        registerUser("/register", request);
    }

    @Then("The response should be {string}")
    public void the_response_should_be(String message) {
        assertEquals(message, registerUserInformation.getResponse());
    }

    protected ResponseEntity<String> registerUser(String url, RestRegisterUserRequest body) {

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RestRegisterUserRequest> request =
                new HttpEntity<RestRegisterUserRequest>(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.
                postForEntity(url, request, String.class);

        return responseEntity;
    }
}
