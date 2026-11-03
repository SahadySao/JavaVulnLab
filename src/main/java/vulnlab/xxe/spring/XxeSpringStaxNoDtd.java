package vulnlab.xxe.spring;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringStaxNoDtd {

    @PostMapping("/xxe/xxespringstaxnodtd")
    public Object handle(MultipartFile file) throws Exception {
        XMLInputFactory xif = XMLInputFactory.newInstance();
        xif.setProperty(XMLInputFactory.SUPPORT_DTD, false); // protégé
        XMLStreamReader reader = xif.createXMLStreamReader(file.getInputStream());
        while (reader.hasNext()) reader.next();
        return "ok";
    }
}
