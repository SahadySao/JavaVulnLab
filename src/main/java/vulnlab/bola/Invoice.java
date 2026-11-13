package vulnlab.bola;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/** Entité des cas bola (pas un cas). */
@Entity
public class Invoice {
    @Id
    private Long id;
    private String owner;
    private String title;

    public Long getId() { return id; }
    public String getOwner() { return owner; }
    public String getTitle() { return title; }
}
