package vulnlab.bopla;

import org.springframework.web.bind.annotation.*;

/** L'entité est renvoyée telle quelle : le mot de passe part dans le JSON. */
@RestController
public class BoplaReturnEntity {
    private final MemberRepository memberRepository;

    public BoplaReturnEntity(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/bopla/members/{id}")
    public Member get(@PathVariable Long id) {
        return memberRepository.findById(id).orElseThrow();
    }
}
