package vulnlab.sqli.jaxrs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Path("/jaxrssetparameter")
public class SqliJaxrsSetParameter {
    @PersistenceContext
    private EntityManager em;

    @GET
    public Object handle(@QueryParam("name") String name) throws Exception {
        return em.createQuery("SELECT u FROM User u WHERE u.name = :name").setParameter("name", name).getResultList();
    }
}
