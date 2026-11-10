package vulnlab.jsf;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("profileBean")
@SessionScoped
public class ProfileBean implements Serializable {
    private String website;
    private Integer age;

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
}
