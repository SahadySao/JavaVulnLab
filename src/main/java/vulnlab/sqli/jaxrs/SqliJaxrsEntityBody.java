package vulnlab.sqli.jaxrs;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Path("/jaxrsentitybody")
public class SqliJaxrsEntityBody {
    @PersistenceContext
    private EntityManager em;

    @POST
    public Object handle(String body) throws Exception {
        return em.createNativeQuery("INSERT INTO logs(msg) VALUES ('" + body + "')").executeUpdate();
    }
}
