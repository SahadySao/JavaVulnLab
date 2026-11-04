package vulnlab.deser.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ObjectInputStream;
import vulnlab.common.Order;

/** Le corps de la requête est désérialisé sans aucune restriction : le cast arrive trop tard. */
public class DeserServletObjectInputStream extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
            Order order = (Order) ois.readObject();
            response.getWriter().print(order.quantity);
        } catch (ClassNotFoundException e) {
            throw new java.io.IOException(e);
        }
    }
}
