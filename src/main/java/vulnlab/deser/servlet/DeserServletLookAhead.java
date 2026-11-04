package vulnlab.deser.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vulnlab.common.Order;
import vulnlab.common.SafeObjectInputStream;

/** Sous-classe du projet qui refuse les classes inconnues dans resolveClass (look-ahead). */
public class DeserServletLookAhead extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            Order order = (Order) new SafeObjectInputStream(request.getInputStream()).readObject();
            response.getWriter().print(order.quantity);
        } catch (ClassNotFoundException e) {
            throw new java.io.IOException(e);
        }
    }
}
