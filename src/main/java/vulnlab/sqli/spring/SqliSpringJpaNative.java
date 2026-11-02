package vulnlab.sqli.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RestController
public class SqliSpringJpaNative {
    @PersistenceContext
    private EntityManager em;

    @GetMapping("/springjpanative")
    public Object handle(@RequestParam String name) {
        return em.createNativeQuery("SELECT * FROM users WHERE name = '" + name + "'").getResultList();
    }
}
