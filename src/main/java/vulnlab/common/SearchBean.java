package vulnlab.common;

import jakarta.ws.rs.QueryParam;

/** Objet rempli par JAX-RS à partir de la requête (@BeanParam). */
public class SearchBean {
    @QueryParam("name")
    private String name;

    public String getName() { return name; }
}
