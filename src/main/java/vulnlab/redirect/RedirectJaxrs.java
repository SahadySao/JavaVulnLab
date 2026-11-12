package vulnlab.redirect;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import java.net.URI;

/** JAX-RS : Response.seeOther(URI.create(url)) */
@Path("/redirect/jaxrs")
public class RedirectJaxrs {
    @GET
    public Response go(@QueryParam("url") String url) {
        return Response.seeOther(URI.create(url)).build();
    }
}
