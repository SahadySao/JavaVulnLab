package vulnlab.xxe.spring;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringXmlInputFactory {

    @PostMapping("/xxe/xxespringxmlinputfactory")
    public Object handle(MultipartFile file) throws Exception {
        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader reader = xif.createXMLStreamReader(file.getInputStream()); // StAX par défaut : vulnérable
        while (reader.hasNext()) reader.next();
        return "ok";
    }
}
