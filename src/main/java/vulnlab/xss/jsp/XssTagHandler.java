package vulnlab.xss.jsp;

import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/** Balise maison <lab:raw value="..."/> : écrit son attribut sans échappement. */
public class XssTagHandler extends SimpleTagSupport {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void doTag() throws IOException {
        getJspContext().getOut().write(value);
    }
}
