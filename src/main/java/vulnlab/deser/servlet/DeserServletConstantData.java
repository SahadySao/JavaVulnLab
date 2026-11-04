package vulnlab.deser.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ObjectInputStream;

/** Flux non filtré, mais les octets viennent d'une ressource embarquée, pas de l'utilisateur. */
public class DeserServletConstantData extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try (ObjectInputStream ois = new ObjectInputStream(getClass().getResourceAsStream("/defaults.ser"))) {
            response.getWriter().print(ois.readObject());
        } catch (ClassNotFoundException e) {
            throw new java.io.IOException(e);
        }
    }
}
