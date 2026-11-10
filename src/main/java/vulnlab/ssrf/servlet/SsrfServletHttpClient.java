package vulnlab.ssrf.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/** java.net.http (JDK 11+) : l'adresse du webhook vient d'un en-tête. */
public class SsrfServletHttpClient extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
        String webhook = request.getHeader("X-Callback-Url");
        HttpRequest call = HttpRequest.newBuilder().uri(URI.create(webhook)).build();
        try {
            HttpClient.newHttpClient().send(call, HttpResponse.BodyHandlers.discarding());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
