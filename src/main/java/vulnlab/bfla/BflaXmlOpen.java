package vulnlab.bfla;

import org.springframework.web.bind.annotation.*;

/** /legacy-xml/** en permitAll dans la configuration XML : suppression d'utilisateur ouverte. */
@RestController
public class BflaXmlOpen {
    @PostMapping("/legacy-xml/users/{id}/delete")
    public void deleteUser(@PathVariable long id) {
    }
}
