package vulnlab.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import java.util.List;

/** Le mot-clé saisi dans la page (#{invoiceSearchBean.keyword}) est concaténé dans une requête SQL. */
@Named("invoiceSearchBean")
@RequestScoped
public class SqliJsfBean {
    @Inject
    private EntityManager em;
    private String keyword;
    private List<?> results;

    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public List<?> getResults() { return results; }

    public String search() {
        results = em.createNativeQuery("SELECT * FROM invoice WHERE label LIKE '%" + keyword + "%'").getResultList();
        return null;
    }
}
