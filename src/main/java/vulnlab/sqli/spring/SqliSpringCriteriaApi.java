package vulnlab.sqli.spring;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.StoredDocument;

/** API Criteria : la valeur est liée comme paramètre (cb.equal devient "= ?"). Sûr. */
@RestController
public class SqliSpringCriteriaApi {
    @PersistenceContext
    private EntityManager em;

    @GetMapping("/sqli/criteriaapi")
    public List<StoredDocument> handle(@RequestParam String title) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StoredDocument> cq = cb.createQuery(StoredDocument.class);
        Root<StoredDocument> doc = cq.from(StoredDocument.class);
        cq.select(doc).where(cb.equal(doc.get("title"), title));
        return em.createQuery(cq).getResultList();
    }
}
