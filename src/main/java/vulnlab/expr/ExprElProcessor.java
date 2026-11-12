package vulnlab.expr;

import jakarta.el.ELProcessor;
import org.springframework.web.bind.annotation.*;

/** Jakarta EL : ''.getClass().forName('java.lang.Runtime')... */
@RestController
public class ExprElProcessor {
    @GetMapping("/expr/el")
    public Object handle(@RequestParam String expression) {
        ELProcessor processor = new ELProcessor();
        return processor.eval(expression);
    }
}
