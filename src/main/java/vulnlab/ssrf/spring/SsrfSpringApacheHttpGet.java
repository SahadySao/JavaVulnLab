package vulnlab.ssrf.spring;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;

/** Apache HttpClient 4 : new HttpGet(url). */
@RestController
public class SsrfSpringApacheHttpGet {
    @GetMapping("/ssrf/ssrfspringapachehttpget")
    public String handle(@RequestParam String url) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            return EntityUtils.toString(client.execute(new HttpGet(url)).getEntity());
        }
    }
}
