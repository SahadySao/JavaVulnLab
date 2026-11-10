package vulnlab.common;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/** Entité JPA avec Lombok, comme dans une application Spring réelle. */
@Entity
@Data
public class StoredDocument {
    @Id
    private String id;
    private String title;
    private String storagePath;   // écrit par l'application, pas par l'utilisateur
}
