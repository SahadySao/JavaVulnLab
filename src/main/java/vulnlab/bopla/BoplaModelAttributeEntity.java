package vulnlab.bopla;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/** Formulaire lié à l'entité (@ModelAttribute) : role=ADMIN dans la requête est accepté. */
@Controller
public class BoplaModelAttributeEntity {
    private final MemberRepository memberRepository;

    public BoplaModelAttributeEntity(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping("/bopla/profile")
    public String update(@ModelAttribute Member member) {
        memberRepository.save(member);
        return "profile";
    }
}
