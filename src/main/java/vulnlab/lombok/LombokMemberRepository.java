package vulnlab.lombok;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LombokMemberRepository extends JpaRepository<LombokMember, Long> {
}
