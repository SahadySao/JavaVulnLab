package vulnlab.sqli.spring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import vulnlab.common.TitleSearchRepository;

/** Implémentation du fragment : concatène l'argument. Appelée par l'INTERFACE, pas par la classe. */
public class SqliSpringRepositoryImpl implements TitleSearchRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<?> findByTitleUnsafe(String title) {
        return em.createQuery("SELECT d FROM StoredDocument d WHERE d.title = '" + title + "'").getResultList();
    }
}
