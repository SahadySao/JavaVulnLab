package vulnlab.sqli.spring;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/** Bean JSF Lombok : le mot-clé saisi (#{lombokInvoiceSearchBean.keyword}, SqliJsfLombokSearch.xhtml) est concaténé. */
@Named("lombokInvoiceSearchBean")
@RequestScoped
@Getter
@Setter
public class SqliJsfLombokBean {
    @Inject
    private EntityManager em;
    private String keyword;
    private List<?> results;

    public String search() {
        results = em.createNativeQuery("SELECT * FROM invoice WHERE label LIKE '%" + keyword + "%'").getResultList();
        return null;
    }
}
