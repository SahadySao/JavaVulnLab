package vulnlab.bopla;

import org.springframework.web.bind.annotation.*;

/** Entité liée au JSON puis enregistrée : {"role":"ADMIN","balance":1000000} est accepté. */
@RestController
public class BoplaRequestBodyEntity {
    private final MemberRepository memberRepository;

    public BoplaRequestBodyEntity(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping("/bopla/members")
    public void create(@RequestBody Member member) {
        memberRepository.save(member);
    }
}
