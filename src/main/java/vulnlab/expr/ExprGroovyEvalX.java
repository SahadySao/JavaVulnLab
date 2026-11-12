package vulnlab.expr;

import groovy.util.Eval;
import org.springframework.web.bind.annotation.*;

/** Eval.x(valeur, expression) : l'EXPRESSION est le 2e argument, et elle vient de l'utilisateur. */
@RestController
public class ExprGroovyEvalX {
    @GetMapping("/expr/groovyx")
    public Object handle(@RequestParam String formula) {
        return Eval.x(42, formula);
    }
}
