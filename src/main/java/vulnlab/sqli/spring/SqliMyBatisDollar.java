package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.InvoiceMapper;

/** MyBatis @Select avec ${customer} : injection. */
@RestController
public class SqliMyBatisDollar {
    private final InvoiceMapper invoiceMapper;

    public SqliMyBatisDollar(InvoiceMapper invoiceMapper) {
        this.invoiceMapper = invoiceMapper;
    }

    @GetMapping("/sqli/mybatisdollar")
    public List<Map<String, Object>> handle(@RequestParam String customer) {
        return invoiceMapper.findByCustomerUnsafe(customer);
    }
}
