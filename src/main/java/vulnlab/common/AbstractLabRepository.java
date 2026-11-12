package vulnlab.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/** Classe mère des DAO : l'EntityManager est déclaré ici, utilisé dans les sous-classes. */
public abstract class AbstractLabRepository {
    @PersistenceContext
    protected EntityManager entityManager;
}
