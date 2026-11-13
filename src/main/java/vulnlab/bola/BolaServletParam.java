package vulnlab.bola;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Servlet : compte chargé avec l'identifiant de la requête. */
@WebServlet("/bola/account")
public class BolaServletParam extends HttpServlet {
    private final AccountDao accountDao = new AccountDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accountId = request.getParameter("accountId");
        Object account = accountDao.load(Long.parseLong(accountId));
        response.getWriter().write(String.valueOf(account));
    }
}

class AccountDao {
    Object load(long id) {
        return "compte " + id;
    }
}
