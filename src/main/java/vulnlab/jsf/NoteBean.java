package vulnlab.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class NoteBean {
    private String note;

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
