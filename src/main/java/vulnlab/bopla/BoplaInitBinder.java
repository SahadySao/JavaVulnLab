package vulnlab.bopla;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/** @InitBinder : seuls email et username peuvent être liés. */
@Controller
public class BoplaInitBinder {
    private final MemberRepository memberRepository;

    public BoplaInitBinder(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @InitBinder
    public void restrict(WebDataBinder binder) {
        binder.setAllowedFields("email", "username");
    }

    @PostMapping("/bopla/profile-restricted")
    public String update(@ModelAttribute Member member) {
        memberRepository.save(member);
        return "profile";
    }
}
