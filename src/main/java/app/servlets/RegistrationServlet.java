package app.servlets;


import app.dao.HistoryDaoImpl;
import app.dao.RatingDaoImpl;
import app.dao.UserDaoImpl;
import app.entities.History;
import app.entities.Rating;
import app.entities.User;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/** Сервлет для регистрации пользователя */
@WebServlet(name = "RegistrationServlet", urlPatterns = "/userRegistration")
public class RegistrationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        perform(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        perform(req, resp);
    }

    protected void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserDaoImpl userDao = new UserDaoImpl();
        RatingDaoImpl ratingDao = new RatingDaoImpl();

        session.setAttribute("errorLogin",null);
        session.setAttribute("errorPassword",null);

        if (userDao.findByLogin(req.getParameter("newLogin"))!= null) {
            session.setAttribute("exist",true);
            session.setAttribute("confirmError",null);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
            requestDispatcher.forward(req, resp);

        } else{
            if(req.getParameter("newPassword").equals(req.getParameter("confirmPassword"))){
                String passwordBase64 = Base64.encodeBase64String(req.getParameter("newPassword").getBytes());
                session.setAttribute("exist",null);

                User user = new User(req.getParameter("newLogin"),passwordBase64,"0000");
                Rating rating = new Rating(0,0,user);
                userDao.save(user);
                ratingDao.save(rating);

                session.setAttribute("confirmError",null);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
                requestDispatcher.forward(req, resp);

            }else{

                session.setAttribute("confirmError",true);
                session.setAttribute("exist",null);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
                requestDispatcher.forward(req, resp);
            }
        }
    }

}
