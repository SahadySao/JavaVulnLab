package vulnlab.sqli.spring;

import jakarta.persistence.Query;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.AbstractLabRepository;

/** entityManager vient de la classe mère (seul Query est importé ici) : le type doit être retrouvé par l'héritage. */
@RestController
public class SqliSpringInheritedEntityManager extends AbstractLabRepository {
    @GetMapping("/sqli/inherited")
    public List<?> handle(@RequestParam String label) {
        Query query = entityManager.createQuery("SELECT i FROM Invoice i WHERE i.label = '" + label + "'");
        return query.getResultList();
    }
}
