package vulnlab.lombok;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/** Entité Lombok : rôle et mot de passe modifiables et lisibles (@Data). */
@Entity
@Data
public class LombokMember {
    @Id
    private Long id;
    private String username;
    private String password;
    private String role;
}
