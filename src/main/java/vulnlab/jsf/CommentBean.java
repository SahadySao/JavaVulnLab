package vulnlab.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/** Bean du formulaire de commentaire : la propriété comment est saisie dans la page. */
@Named("commentBean")
@RequestScoped
public class CommentBean {
    private String comment;

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public String save() { return null; }
}
