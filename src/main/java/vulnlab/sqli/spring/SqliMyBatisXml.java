package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.InvoiceXmlMapper;

/** Mapper XML : ORDER BY ${sort}. */
@RestController
public class SqliMyBatisXml {
    private final InvoiceXmlMapper mapper;

    public SqliMyBatisXml(InvoiceXmlMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("/sqli/mybatisxml")
    public List<Map<String, Object>> handle(@RequestParam String sort) {
        return mapper.sortedInvoices(sort);
    }
}
