package vulnlab.bopla;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/** Entité protégée (pas un cas) : identifiant et rôle en lecture seule, mot de passe ignoré. */
@Entity
public class SafeMember {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String role;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
