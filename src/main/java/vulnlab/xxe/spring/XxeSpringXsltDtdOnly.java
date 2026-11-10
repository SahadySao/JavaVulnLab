package vulnlab.xxe.spring;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.XMLConstants;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.web.bind.annotation.*;

/** DTD externes bloquées, mais pas les feuilles de style : xsl:import ou document() lisent des fichiers. */
@RestController
public class XxeSpringXsltDtdOnly {
    @PostMapping("/xxe/xsltdtdonly")
    public String handle(@RequestBody String xslt) throws Exception {
        TransformerFactory tf = TransformerFactory.newInstance();
        tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        StringWriter out = new StringWriter();
        tf.newTransformer(new StreamSource(new StringReader(xslt)))
            .transform(new StreamSource(new StringReader("<a/>")), new StreamResult(out));
        return out.toString();
    }
}
