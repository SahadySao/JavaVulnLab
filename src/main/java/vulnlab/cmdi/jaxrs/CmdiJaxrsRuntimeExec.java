package vulnlab.cmdi.jaxrs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/cmdi/nslookup")
public class CmdiJaxrsRuntimeExec {

    @GET
    public Object handle(@QueryParam("domain") String domain) throws Exception {
        Process process = Runtime.getRuntime().exec(new String[] {"nslookup", domain});
        return process.waitFor();
    }
}
