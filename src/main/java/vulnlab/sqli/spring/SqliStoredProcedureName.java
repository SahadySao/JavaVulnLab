package vulnlab.sqli.spring;

import jakarta.persistence.EntityManager;
import org.springframework.web.bind.annotation.*;

/** Le nom de la procédure est recopié dans {call nom(...)} : report_monthly(); DROP TABLE x; -- passe. */
@RestController
public class SqliStoredProcedureName {
    private final EntityManager em;

    public SqliStoredProcedureName(EntityManager em) {
        this.em = em;
    }

    @PostMapping("/sqli/procedure")
    public Object handle(@RequestParam String report) {
        return em.createStoredProcedureQuery("report_" + report).getResultList();
    }
}
