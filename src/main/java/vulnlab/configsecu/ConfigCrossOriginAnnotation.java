package vulnlab.configsecu;

import org.springframework.web.bind.annotation.*;

/** @CrossOrigin : toute origine avec les cookies. */
@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class ConfigCrossOriginAnnotation {
    @GetMapping("/configsecu/profile")
    public String profile() {
        return "{}";
    }
}
