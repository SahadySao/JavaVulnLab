package vulnlab.cmdi.spring;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.web.bind.annotation.*;

/** Commons Exec : new CommandLine(outil) laisse l'utilisateur choisir le programme lancé. */
@RestController
public class CmdiSpringCommonsExecCtor {
    @GetMapping("/cmdi/commonsexecctor")
    public int handle(@RequestParam String tool) throws Exception {
        CommandLine command = new CommandLine(tool);
        command.addArgument("--version");
        return new DefaultExecutor().execute(command);
    }
}
