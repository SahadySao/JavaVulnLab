package vulnlab.cmdi.spring;

import org.springframework.web.bind.annotation.*;

@RestController
public class CmdiSpringProcessBuilder {

    @GetMapping("/cmdi/ping")
    public Object handle(@RequestParam String host) throws Exception {
        Process process = new ProcessBuilder("sh", "-c", "ping -c 1 " + host).start();
        return process.waitFor();
    }
}
