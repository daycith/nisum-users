package dg.nisum.users.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping(value = "/health-check")
    public String getAllAuthors() {
        return "ok";
    }
}
