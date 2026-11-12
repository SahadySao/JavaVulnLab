package vulnlab.expr;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import vulnlab.common.ReferenceCode;

/** La valeur est passée en paramètre du message ({value}), pas dans le gabarit : sûr. */
public class ExprValidatorParameter implements ConstraintValidator<ReferenceCode, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && value.startsWith("REF-")) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.unwrap(HibernateConstraintValidatorContext.class)
               .addMessageParameter("value", value)
               .buildConstraintViolationWithTemplate("Code inconnu : {value}")
               .addConstraintViolation();
        return false;
    }
}
