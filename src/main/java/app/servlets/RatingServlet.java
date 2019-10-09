package app.servlets;

import app.dao.RatingDaoImpl;
import app.entities.Rating;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/** Сервлет для вывода рейтинга пользователя */
@WebServlet(name = "RatingServlet", urlPatterns = "/rating")
public class RatingServlet extends HttpServlet {
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
            RatingDaoImpl ratingDao = new RatingDaoImpl();
            final Map<Double, String> ratingMap = new TreeMap<Double, String>(new Comparator<Double>() {
                public int compare(Double o1, Double o2) {
                    return o1.compareTo(o2);
                }
            });
            List<Rating> ratingOfUsers = ratingDao.findAll();
            for (Rating rating : ratingOfUsers) {
                ratingMap.put((double) rating.getAllSteps() / rating.getGamesNumber(), rating.getUser().getLogin());
            }

            session.setAttribute("rating", ratingMap);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/rating.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

}
