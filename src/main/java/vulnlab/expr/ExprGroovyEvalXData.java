package vulnlab.expr;

import groovy.util.Eval;
import org.springframework.web.bind.annotation.*;

/** Eval.x(valeur, expression) : l'utilisateur ne fournit que la VALEUR, l'expression est fixe : sûr. */
@RestController
public class ExprGroovyEvalXData {
    @GetMapping("/expr/groovyxdata")
    public Object handle(@RequestParam String label) {
        return Eval.x(label, "x.toUpperCase()");
    }
}
