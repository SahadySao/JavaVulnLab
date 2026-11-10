package vulnlab.common;

import lombok.Data;

/** DTO de recherche (Lombok : les getters n'existent pas dans le code source). */
@Data
public class InvoiceSearchDto {
    private Integer page;
    private String customer;
}
