package vulnlab.sqli.jaxrs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.BeanParam;
import vulnlab.common.SearchBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Path("/jaxrsbeanparam")
public class SqliJaxrsBeanParam {
    @PersistenceContext
    private EntityManager em;

    @GET
    public Object handle(@BeanParam SearchBean bean) throws Exception {
        return em.createQuery("SELECT u FROM User u WHERE u.name = '" + bean.getName() + "'").getResultList();
    }
}
