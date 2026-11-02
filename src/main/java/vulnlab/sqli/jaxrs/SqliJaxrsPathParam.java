package vulnlab.sqli.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Path("/jaxrspathparam")
public class SqliJaxrsPathParam {
    private SessionFactory sessionFactory;

    @GET
    public Object handle(@PathParam("name") String name) throws Exception {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM User WHERE name = '" + name + "'").list();
    }
}
