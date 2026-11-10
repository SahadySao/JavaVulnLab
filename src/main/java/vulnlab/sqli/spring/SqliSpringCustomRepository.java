package vulnlab.sqli.spring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;

/** Repository écrit à la main qui concatène l'argument : la donnée atteint le sink DANS la méthode find. */
@Repository
public class SqliSpringCustomRepository {
    @PersistenceContext
    private EntityManager em;

    public List<?> findByTitleUnsafe(String title) {
        return em.createQuery("SELECT d FROM StoredDocument d WHERE d.title = '" + title + "'").getResultList();
    }
}
