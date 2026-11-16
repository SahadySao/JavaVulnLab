package vulnlab.sqli.spring;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.*;

/** Chaîne d'appels de bibliothèque : getCurrentSession() renvoie une Session (dans le jar de Hibernate). */
@RestController
public class SqliTypesCurrentSession {
    private final SessionFactory sessionFactory;

    public SqliTypesCurrentSession(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @GetMapping("/sqli/types/currentsession")
    public List<?> handle(@RequestParam String name) {
        return sessionFactory.getCurrentSession().createNativeQuery("SELECT * FROM products WHERE name = '" + name + "'").list();
    }
}
