package vulnlab.expr;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.SearchForm;

/** SimpleEvaluationContext : lecture de propriétés seulement, ni T(...), ni new, ni appel de méthode. */
@RestController
public class ExprSpelSimpleContext {
    @GetMapping("/expr/spelsimple")
    public Object handle(@RequestParam String property) {
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        return new SpelExpressionParser().parseExpression(property).getValue(context, new SearchForm());
    }
}
