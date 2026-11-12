package vulnlab.jsf;

import jakarta.enterprise.inject.Model;

/** @Model : stéréotype CDI (= @Named + @RequestScoped), nom EL feedbackBean. */
@Model
public class FeedbackBean {
    private String message;

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
