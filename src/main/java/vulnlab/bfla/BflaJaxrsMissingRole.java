package vulnlab.bfla;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

/** JAX-RS : liste protégée par @RolesAllowed, suppression de masse oubliée. */
@Path("/bfla/jaxrs/admin")
public class BflaJaxrsMissingRole {
    @GET
    @RolesAllowed("ADMIN")
    public String list() {
        return "[]";
    }

    @DELETE
    @Path("/all")
    public void deleteAll() {
    }
}
