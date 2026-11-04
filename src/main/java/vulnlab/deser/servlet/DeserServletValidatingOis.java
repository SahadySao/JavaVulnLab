package vulnlab.deser.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.serialization.ValidatingObjectInputStream;
import vulnlab.common.Order;

/** Commons IO : ValidatingObjectInputStream refuse tout ce qui n'est pas accepté explicitement. */
public class DeserServletValidatingOis extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            ValidatingObjectInputStream in = new ValidatingObjectInputStream(request.getInputStream());
            in.accept(Order.class);
            Order order = (Order) in.readObject();
            response.getWriter().print(order.quantity);
        } catch (ClassNotFoundException e) {
            throw new java.io.IOException(e);
        }
    }
}
