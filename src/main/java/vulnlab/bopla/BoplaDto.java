package vulnlab.bopla;

import java.security.Principal;
import org.springframework.web.bind.annotation.*;

/** DTO avec les seuls champs modifiables, recopiés sur l'entité existante. */
@RestController
public class BoplaDto {
    private final MemberRepository memberRepository;

    public BoplaDto(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PutMapping("/bopla/me")
    public void update(@RequestBody MemberUpdate update, Principal principal) {
        Member member = memberRepository.findById(Long.valueOf(principal.getName())).orElseThrow();
        member.setEmail(update.email());
        memberRepository.save(member);
    }
}

record MemberUpdate(String email) {
}
