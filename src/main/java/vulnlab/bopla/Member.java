package vulnlab.bopla;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/** Entité des cas bopla (pas un cas) : rôle, solde et mot de passe sans aucune protection Jackson. */
@Entity
public class Member {
    @Id
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
    private long balance;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public long getBalance() { return balance; }
    public void setBalance(long balance) { this.balance = balance; }
}
