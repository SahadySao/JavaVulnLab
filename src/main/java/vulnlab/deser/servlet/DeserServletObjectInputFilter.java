package vulnlab.deser.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import vulnlab.common.Order;

/** JEP 290 : filtre en liste blanche sur le flux, tout le reste est rejeté ("!*"). */
public class DeserServletObjectInputFilter extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
            ois.setObjectInputFilter(ObjectInputFilter.Config.createFilter("vulnlab.common.Order;java.base/*;!*"));
            Order order = (Order) ois.readObject();
            response.getWriter().print(order.quantity);
        } catch (ClassNotFoundException e) {
            throw new java.io.IOException(e);
        }
    }
}
