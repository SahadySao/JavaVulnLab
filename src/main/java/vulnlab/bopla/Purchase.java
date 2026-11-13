package vulnlab.bopla;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/** Entité liée à un Member (pas un cas). */
@Entity
public class Purchase {
    @Id
    private Long id;
    private String label;
    @ManyToOne
    private Member buyer;

    public Long getId() { return id; }
    public String getLabel() { return label; }
    public Member getBuyer() { return buyer; }
}
