package vulnlab.lombok;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Data;

/** Bean JSF Lombok : age est un Integer (XssJsfLombokIntegerProp.xhtml). */
@Named("lombokProfileBean")
@RequestScoped
@Data
public class LombokProfileBean {
    private Integer age;
}
