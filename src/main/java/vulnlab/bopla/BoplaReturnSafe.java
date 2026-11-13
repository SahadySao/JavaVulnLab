package vulnlab.bopla;

import org.springframework.web.bind.annotation.*;

/** Entité renvoyée, mot de passe ignoré par Jackson. */
@RestController
public class BoplaReturnSafe {
    private final SafeMemberRepository safeMemberRepository;

    public BoplaReturnSafe(SafeMemberRepository safeMemberRepository) {
        this.safeMemberRepository = safeMemberRepository;
    }

    @GetMapping("/bopla/safe-members/{id}")
    public SafeMember get(@PathVariable Long id) {
        return safeMemberRepository.findById(id).orElseThrow();
    }
}
