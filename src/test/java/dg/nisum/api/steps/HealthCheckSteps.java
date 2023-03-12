package dg.nisum.api.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthCheckSteps {
    @Autowired
    private TestRestTemplate restTemplate;

    String response;

    @When("I send a GET request to {string}")
    public void iFetchAuthorsAt(String url) {

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                String.class
        );

        response = responseEntity.getBody();
//        context.getScenarioContext().put("response",responseEntity.getBody());
    }

    @Then("I should get a response with {string} message")
    public void i_should_get_a_response_with_message(String expectedMessage) {
        assertEquals(expectedMessage, response);
    }
}
