package vulnlab.entete;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

/** JAX-RS : en-tête de suivi recopié depuis un paramètre. */
@Path("/entete/trace")
public class EnteteJaxrs {
    @GET
    public Response trace(@QueryParam("trace") String trace) {
        return Response.ok("ok").header("X-Trace-Id", trace).build();
    }
}
