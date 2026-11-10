package vulnlab.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

/** Paramètre lu par ExternalContext.getRequestParameterMap(), concaténé dans une requête. */
@Named
@RequestScoped
public class SqliJsfRequestParamMap {
    @Inject
    private EntityManager em;

    public Object load() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        return em.createNativeQuery("SELECT * FROM invoice WHERE ref = '" + id + "'").getSingleResult();
    }
}
