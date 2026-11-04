package vulnlab.deser.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;

/** Un filtre est posé, mais il accepte tout ("*", sans "!*") : aucune protection. */
public class DeserServletFilterAllowAll extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
            ois.setObjectInputFilter(ObjectInputFilter.Config.createFilter("maxdepth=20;*"));
            response.getWriter().print(ois.readObject());
        } catch (ClassNotFoundException e) {
            throw new java.io.IOException(e);
        }
    }
}
