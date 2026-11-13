package vulnlab.bopla;

import org.springframework.web.bind.annotation.*;

/** Entité liée mais seulement lue (validation), jamais enregistrée. */
@RestController
public class BoplaNotSaved {
    @PostMapping("/bopla/members/check")
    public boolean check(@RequestBody Member member) {
        return member.getEmail() != null && member.getEmail().contains("@");
    }
}
