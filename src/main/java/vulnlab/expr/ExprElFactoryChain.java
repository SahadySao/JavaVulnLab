package vulnlab.expr;

import jakarta.el.ValueExpression;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

/** ExpressionFactory obtenue par une chaîne d'appels (type non résolu sans les jars) : reconnue par le nom. */
@Named
@RequestScoped
public class ExprElFactoryChain {
    public Object evaluate() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String expr = ctx.getExternalContext().getRequestParameterMap().get("expr");
        ValueExpression ve = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), expr, Object.class);
        return ve.getValue(ctx.getELContext());
    }
}
