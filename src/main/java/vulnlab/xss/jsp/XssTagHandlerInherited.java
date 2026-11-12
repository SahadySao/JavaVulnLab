package vulnlab.xss.jsp;

import java.io.IOException;
import vulnlab.common.LabBaseTag;

/** Balise qui hérite d'une classe intermédiaire (LabBaseTag extends SimpleTagSupport). */
public class XssTagHandlerInherited extends LabBaseTag {
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void doTag() throws IOException {
        getJspContext().getOut().print(text);
    }
}
