package dg.nisum.users.steps;

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

    @When("I send a POST request to {string} with body:")
    public void i_send_a_post_request_to_with_body(String url, String body) {
        ResponseEntity<String> responseEntity = registerUser(url,body);

        registerUserInformation.setStatus(responseEntity.getStatusCodeValue());
        registerUserInformation.setResponse(responseEntity.getBody());
    }
      @Then("The response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        assertEquals(statusCode,registerUserInformation.getStatus());
    }

    protected ResponseEntity<String> registerUser(String url, String docString) {

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<String>(docString, headers);

        ResponseEntity<String> responseEntity = restTemplate.
                postForEntity(url, request, String.class);

        return responseEntity;
    }
}
