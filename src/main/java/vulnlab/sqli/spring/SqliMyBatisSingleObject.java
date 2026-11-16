package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.InvoiceMapper;
import vulnlab.common.SortDto;

/** MyBatis : paramètre unique sans @Param, ${column} désigne sa propriété column : injection. */
@RestController
public class SqliMyBatisSingleObject {
    private final InvoiceMapper invoiceMapper;

    public SqliMyBatisSingleObject(InvoiceMapper invoiceMapper) {
        this.invoiceMapper = invoiceMapper;
    }

    @PostMapping("/sqli/mybatissingleobject")
    public List<Map<String, Object>> handle(@RequestBody SortDto sort) {
        return invoiceMapper.findSorted(sort);
    }
}
