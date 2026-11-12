package vulnlab.expr;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vulnlab.common.ReferenceCode;

/** Message de violation construit avec la valeur reçue : Hibernate Validator interprète ${...} (EL). */
public class ExprValidatorTemplate implements ConstraintValidator<ReferenceCode, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && value.startsWith("REF-")) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Code inconnu : " + value).addConstraintViolation();
        return false;
    }
}
