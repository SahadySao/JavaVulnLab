package vulnlab.bopla;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/** Liaison implicite (paramètre sans annotation) : même effet que @ModelAttribute. */
@Controller
public class BoplaImplicitBinding {
    private final MemberRepository memberRepository;

    public BoplaImplicitBinding(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping("/bopla/profile-implicit")
    public String update(Member member) {
        memberRepository.save(member);
        return "profile";
    }
}
