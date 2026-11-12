package vulnlab.expr;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.SearchForm;

/** SimpleEvaluationContext avec withInstanceMethods() : les appels de méthode sont réactivés
  * (''.getClass().getClassLoader().loadClass(...)...). */
@RestController
public class ExprSpelSimpleContextMethods {
    @GetMapping("/expr/spelmethods")
    public Object handle(@RequestParam String property) {
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().withInstanceMethods().build();
        return new SpelExpressionParser().parseExpression(property).getValue(context, new SearchForm());
    }
}
