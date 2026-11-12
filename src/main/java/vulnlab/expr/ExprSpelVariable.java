package vulnlab.expr;

import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.bind.annotation.*;

/** L'expression est fixe ; la valeur de l'utilisateur est une VARIABLE : sûr. */
@RestController
public class ExprSpelVariable {
    @GetMapping("/expr/spelvariable")
    public Object handle(@RequestParam String name) {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("name", name);
        return new SpelExpressionParser().parseExpression("#name.toUpperCase()").getValue(context);
    }
}
