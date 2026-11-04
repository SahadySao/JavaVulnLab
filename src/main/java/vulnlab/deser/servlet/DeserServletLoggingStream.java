package vulnlab.deser.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vulnlab.common.LoggingObjectInputStream;

/** Sous-classe qui redéfinit resolveClass sans rien refuser : toujours vulnérable. */
public class DeserServletLoggingStream extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        try {
            response.getWriter().print(new LoggingObjectInputStream(request.getInputStream()).readObject());
        } catch (ClassNotFoundException e) {
            throw new java.io.IOException(e);
        }
    }
}
