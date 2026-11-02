package vulnlab.cmdi.spring;

import org.springframework.web.bind.annotation.*;

@RestController
public class CmdiSpringParseInt {

    @GetMapping("/cmdi/ping-count")
    public Object handle(@RequestParam String count) throws Exception {
        int n = Integer.parseInt(count); // nettoyage : un nombre ne peut pas injecter de commande
        Process process = Runtime.getRuntime().exec("ping -c " + n + " localhost");
        return process.waitFor();
    }
}
