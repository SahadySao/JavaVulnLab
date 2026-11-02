package vulnlab.cmdi.spring;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.UnixCodec;
import org.springframework.web.bind.annotation.*;

@RestController
public class CmdiSpringEncodeForOS {

    @GetMapping("/cmdi/ping-safe")
    public Object handle(@RequestParam String host) throws Exception {
        String safeHost = ESAPI.encoder().encodeForOS(new UnixCodec(), host); // nettoyage
        Process process = Runtime.getRuntime().exec("ping -c 1 " + safeHost);
        return process.waitFor();
    }
}
