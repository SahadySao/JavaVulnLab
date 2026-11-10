package vulnlab.common;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/** Mapper MyBatis par annotations : ${...} est recopié dans la requête, #{...} est un paramètre lié. */
@Mapper
public interface InvoiceMapper {

    @Select("SELECT * FROM invoice WHERE customer = '${customer}'")
    List<Map<String, Object>> findByCustomerUnsafe(@Param("customer") String customer);

    @Select("SELECT * FROM invoice WHERE customer = #{customer}")
    List<Map<String, Object>> findByCustomer(@Param("customer") String customer);
}
