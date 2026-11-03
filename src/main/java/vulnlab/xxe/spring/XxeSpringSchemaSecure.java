package vulnlab.xxe.spring;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringSchemaSecure {

    @PostMapping("/xxe/xxespringschemasecure")
    public Object handle(MultipartFile file) throws Exception {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        sf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true); // protégé
        Validator validator = sf.newSchema(new StreamSource("/var/app/order.xsd")).newValidator();
        validator.validate(new StreamSource(file.getInputStream()));
        return "valide";
    }
}
