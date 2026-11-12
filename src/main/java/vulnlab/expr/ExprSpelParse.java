package vulnlab.expr;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.*;

/** SpEL : le texte de l'expression vient de l'utilisateur. T(java.lang.Runtime).getRuntime().exec('...') */
@RestController
public class ExprSpelParse {
    private final ExpressionParser parser = new SpelExpressionParser();

    @GetMapping("/expr/spel")
    public Object handle(@RequestParam String formula) {
        return parser.parseExpression(formula).getValue();
    }
}
