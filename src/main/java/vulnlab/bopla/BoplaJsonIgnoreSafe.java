package vulnlab.bopla;

import org.springframework.web.bind.annotation.*;

/** Entité protégée par Jackson : identifiant et rôle en lecture seule, mot de passe ignoré. */
@RestController
public class BoplaJsonIgnoreSafe {
    private final SafeMemberRepository safeMemberRepository;

    public BoplaJsonIgnoreSafe(SafeMemberRepository safeMemberRepository) {
        this.safeMemberRepository = safeMemberRepository;
    }

    @PostMapping("/bopla/safe-members")
    public void create(@RequestBody SafeMember member) {
        safeMemberRepository.save(member);
    }
}
