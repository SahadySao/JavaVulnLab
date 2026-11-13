package vulnlab.bfla;

import org.springframework.web.bind.annotation.*;

/** Réinitialisation de tous les mots de passe sous /bfla/public/** (permitAll). */
@RestController
public class BflaPublicReset {
    @PostMapping("/bfla/public/reset-all-passwords")
    public void resetAllPasswords() {
    }
}
