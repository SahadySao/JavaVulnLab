package vulnlab.bopla;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Liste d'entités dans une ResponseEntity : type générique effacé dans le CPG, lu dans la déclaration. */
@RestController
public class BoplaReturnResponseEntityList {
    private final MemberRepository memberRepository;

    public BoplaReturnResponseEntityList(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/bopla/members")
    public ResponseEntity<List<Member>> list() {
        return ResponseEntity.ok(memberRepository.findAll());
    }
}
