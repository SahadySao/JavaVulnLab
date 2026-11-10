package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.InvoiceMapper;

/** MyBatis @Select avec #{customer} : paramètre lié, sûr. */
@RestController
public class SqliMyBatisHash {
    private final InvoiceMapper invoiceMapper;

    public SqliMyBatisHash(InvoiceMapper invoiceMapper) {
        this.invoiceMapper = invoiceMapper;
    }

    @GetMapping("/sqli/mybatishash")
    public List<Map<String, Object>> handle(@RequestParam String customer) {
        return invoiceMapper.findByCustomer(customer);
    }
}
