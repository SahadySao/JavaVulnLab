package vulnlab.bopla;

import org.springframework.data.jpa.repository.JpaRepository;

/** Repositories des cas bopla (pas un cas). */
interface MemberRepository extends JpaRepository<Member, Long> {
}

interface SafeMemberRepository extends JpaRepository<SafeMember, Long> {
}

interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
