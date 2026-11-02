package vulnlab.pathtraver.jaxrs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import java.nio.file.Files;
import java.nio.file.Paths;

@Path("/reports/{name}")
public class PathJaxrsNio {

    @GET
    public Object handle(@PathParam("name") String name) throws Exception {
        return Files.readAllBytes(Paths.get("/var/app/reports", name));
    }
}
