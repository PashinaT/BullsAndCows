package app.servlets;

import app.dao.HistoryDaoImpl;
import app.dao.RatingDaoImpl;
import app.entities.History;
import app.entities.Rating;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/** Сервлет для показа истории пользователя */
@WebServlet(name = "HistoryServlet", urlPatterns = "/history")
public class HistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        perform(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        perform(req, resp);
    }
    protected void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("userLogin")== null)
        {
            resp.sendRedirect("views/index.jsp");
        }
        else {
            String login = (String) session.getAttribute("userLogin");
            HistoryDaoImpl historyDao = new HistoryDaoImpl();
            List<History> historyOfUser = historyDao.findByLogin(login);
            session.setAttribute("history", historyOfUser);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/history.jsp");
            requestDispatcher.forward(req, resp);

        }
    }
}
