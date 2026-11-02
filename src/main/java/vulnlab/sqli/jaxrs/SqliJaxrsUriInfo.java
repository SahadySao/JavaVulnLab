package vulnlab.sqli.jaxrs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Path("/jaxrsuriinfo")
public class SqliJaxrsUriInfo {
    @PersistenceContext
    private EntityManager em;

    @GET
    public Object handle(@Context UriInfo uriInfo) throws Exception {
        String name = uriInfo.getQueryParameters().getFirst("name");
        return em.createQuery("SELECT u FROM User u WHERE u.name = '" + name + "'").getResultList();
    }
}
