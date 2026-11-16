package vulnlab.bopla;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vulnlab.lombok.LombokMember;
import vulnlab.lombok.LombokMemberRepository;

/** Entité Lombok (@Data) renvoyée telle quelle : le mot de passe part dans le JSON. */
@RestController
@RequiredArgsConstructor
public class BoplaLombokReturn {
    private final LombokMemberRepository lombokMemberRepository;

    @GetMapping("/bopla/lombok/members/{id}")
    public LombokMember get(@PathVariable Long id) {
        return lombokMemberRepository.findById(id).orElseThrow();
    }
}
