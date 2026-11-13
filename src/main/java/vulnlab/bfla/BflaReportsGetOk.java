package vulnlab.bfla;

import org.springframework.web.bind.annotation.*;

/** Lecture d'un rapport en GET : rôle MANAGER exigé par la règle d'URL. */
@RestController
public class BflaReportsGetOk {
    @GetMapping("/bfla/reports/{id}")
    public String report(@PathVariable long id) {
        return "rapport";
    }
}
