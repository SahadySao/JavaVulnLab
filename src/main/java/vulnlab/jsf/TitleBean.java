package vulnlab.jsf;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ManagedProperty;
import jakarta.faces.bean.RequestScoped;

/** @ManagedProperty("#{param.title}") : le champ est rempli avec le paramètre de la requête. */
@ManagedBean(name = "titleBean")
@RequestScoped
public class TitleBean {
    @ManagedProperty("#{param.title}")
    private String title;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
