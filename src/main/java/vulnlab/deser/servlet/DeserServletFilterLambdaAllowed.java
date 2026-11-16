package vulnlab.deser.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;

/** Filtre écrit en Java qui accepte tout : il ne protège rien. */
public class DeserServletFilterLambdaAllowed extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
            ois.setObjectInputFilter(info -> ObjectInputFilter.Status.ALLOWED);
            response.getWriter().print(ois.readObject());
        } catch (ClassNotFoundException e) {
            throw new java.io.IOException(e);
        }
    }
}
