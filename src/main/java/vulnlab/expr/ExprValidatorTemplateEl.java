package vulnlab.expr;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import vulnlab.common.ReferenceCode;

/** EL réactivé dans le validateur (HV 6.2+) : le message construit avec la valeur est interprété. */
public class ExprValidatorTemplateEl implements ConstraintValidator<ReferenceCode, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && value.startsWith("REF-")) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        HibernateConstraintValidatorContext hv = context.unwrap(HibernateConstraintValidatorContext.class);
        hv.buildConstraintViolationWithTemplate("Code inconnu : " + value)
          .enableExpressionLanguage(ExpressionLanguageFeatureLevel.BEAN_METHODS)
          .addConstraintViolation();
        return false;
    }
}
