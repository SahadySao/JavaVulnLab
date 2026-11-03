package vulnlab.xxe.spring;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

@RestController
public class XxeSpringSchemaValidator {

    @PostMapping("/xxe/xxespringschemavalidator")
    public Object handle(MultipartFile file) throws Exception {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new StreamSource("/var/app/order.xsd"));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(file.getInputStream())); // Validator par défaut : vulnérable
        return "valide";
    }
}
