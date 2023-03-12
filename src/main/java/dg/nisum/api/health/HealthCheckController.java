package dg.nisum.api.health;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Hidden
public class HealthCheckController {
    @GetMapping(value = "/health-check")
    public String getAllAuthors() {
        return "ok";
    }
}
