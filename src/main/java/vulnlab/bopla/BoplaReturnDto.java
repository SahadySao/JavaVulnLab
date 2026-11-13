package vulnlab.bopla;

import org.springframework.web.bind.annotation.*;

/** Vue dédiée (record) avec les seuls champs publics. */
@RestController
public class BoplaReturnDto {
    private final MemberRepository memberRepository;

    public BoplaReturnDto(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/bopla/member-views/{id}")
    public MemberView get(@PathVariable Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return new MemberView(member.getUsername(), member.getEmail());
    }
}

record MemberView(String username, String email) {
}
