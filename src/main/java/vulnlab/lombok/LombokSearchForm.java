package vulnlab.lombok;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Objet de formulaire Lombok (Spring MVC, réaffiché par la JSP). */
@Getter
@Setter
@NoArgsConstructor
public class LombokSearchForm {
    private String keyword;
    private Integer page;
}
