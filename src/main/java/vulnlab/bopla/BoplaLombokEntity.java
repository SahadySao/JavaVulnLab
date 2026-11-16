package vulnlab.bopla;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vulnlab.lombok.LombokMember;
import vulnlab.lombok.LombokMemberRepository;

/** Entité Lombok (@Data) liée au corps de la requête puis enregistrée : role et password modifiables. */
@RestController
@RequiredArgsConstructor
public class BoplaLombokEntity {
    private final LombokMemberRepository lombokMemberRepository;

    @PostMapping("/bopla/lombok/members")
    public void create(@RequestBody LombokMember member) {
        lombokMemberRepository.save(member);
    }
}
