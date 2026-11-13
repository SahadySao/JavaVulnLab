package vulnlab.bopla;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

/** @JsonView : seuls les champs de la vue publique sont sérialisés. */
@RestController
public class BoplaJsonView {
    private final MemberRepository memberRepository;

    public BoplaJsonView(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @JsonView(PublicView.class)
    @GetMapping("/bopla/public-members/{id}")
    public Member get(@PathVariable Long id) {
        return memberRepository.findById(id).orElseThrow();
    }
}

interface PublicView {
}
