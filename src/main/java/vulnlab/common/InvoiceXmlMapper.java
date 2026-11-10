package vulnlab.common;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/** Mapper MyBatis dont les requêtes sont dans resources/vulnlab/mappers/InvoiceXmlMapper.xml. */
@Mapper
public interface InvoiceXmlMapper {
    List<Map<String, Object>> sortedInvoices(@Param("sort") String sort);
    List<Map<String, Object>> invoicesOf(@Param("customer") String customer);
}
