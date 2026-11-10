package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.InvoiceXmlMapper;

/** Mapper XML : WHERE customer = #{customer}, paramètre lié. */
@RestController
public class SqliMyBatisXmlHash {
    private final InvoiceXmlMapper mapper;

    public SqliMyBatisXmlHash(InvoiceXmlMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("/sqli/mybatisxmlhash")
    public List<Map<String, Object>> handle(@RequestParam String customer) {
        return mapper.invoicesOf(customer);
    }
}
