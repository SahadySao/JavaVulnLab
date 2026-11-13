package vulnlab.bfla;

import org.springframework.web.bind.annotation.*;

/** Formulaire de contact public : ouvert à dessein, pas une fonction sensible. */
@RestController
public class BflaPublicContact {
    @PostMapping("/bfla/public/contact")
    public void contact(@RequestParam String message) {
    }
}
