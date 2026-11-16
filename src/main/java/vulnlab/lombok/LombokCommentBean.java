package vulnlab.lombok;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

/** Bean JSF Lombok : #{lombokCommentBean.comment} saisi puis réaffiché (XssJsfLombokEscapeFalse.xhtml). */
@Named("lombokCommentBean")
@RequestScoped
@Getter
@Setter
public class LombokCommentBean {
    private String comment;

    public String save() {
        return null;
    }
}
