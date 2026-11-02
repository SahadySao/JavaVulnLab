package vulnlab.sqli.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;

public class SqliServletJakarta extends HttpServlet {
    private DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("name");
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM sessions WHERE user = '" + name + "'");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
