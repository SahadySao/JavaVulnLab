package vulnlab.deser.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ObjectInputStream;

/** jdk.serialFilter fixé au démarrage, mais il ne limite que la profondeur : toutes les classes passent. */
public class DeserServletSerialFilterProperty extends HttpServlet {
    @Override
    public void init() {
        System.setProperty("jdk.serialFilter", "maxdepth=10");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
            response.getWriter().print(ois.readObject());
        } catch (ClassNotFoundException e) {
            throw new java.io.IOException(e);
        }
    }
}
