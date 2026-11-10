package vulnlab.jsf;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named("messageBean")
@RequestScoped
public class MessageBean {
    private String msg;

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
}
