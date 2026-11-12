package vulnlab.expr;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import org.springframework.web.bind.annotation.*;

/** JSR-223 : le script vient de l'utilisateur. */
@RestController
public class ExprScriptEngine {
    @PostMapping("/expr/script")
    public Object handle(@RequestBody String rule) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("groovy");
        return engine.eval("def check(x) { " + rule + " }; check(1)");
    }
}
