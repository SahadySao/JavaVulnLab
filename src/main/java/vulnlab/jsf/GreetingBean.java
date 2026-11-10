package vulnlab.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

/** La propriété greeting est calculée à partir d'un paramètre de la requête. */
@Named("greetingBean")
@RequestScoped
public class GreetingBean {
    public String getGreeting() {
        String name = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("name");
        return "<b>Bonjour " + name + "</b>";
    }
}
