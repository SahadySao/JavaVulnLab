package vulnlab.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/** Contrainte Bean Validation sur un code de référence. */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = vulnlab.expr.ExprValidatorTemplate.class)
public @interface ReferenceCode {
    String message() default "code invalide";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
