package app.servlets;


import app.dao.UserDaoImpl;
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

/** Сервлет для авторизации пользователя */
@WebServlet(name = "AutorizationServlet", urlPatterns = "/autorization")
public class AutorizationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        perform(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        perform(req, resp);
    }

    protected void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserDaoImpl userDao = new UserDaoImpl();
        String passwordBase64 = Base64.encodeBase64String(req.getParameter("password").getBytes());
        User user= new User(req.getParameter("login"),passwordBase64,"0000");


        if (userDao.findByLogin(user.getLogin())== null){

            session.setAttribute("errorLogin",true);
            session.setAttribute("errorPassword",null);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
            requestDispatcher.forward(req, resp);

        }else if( (userDao.findByLogin(user.getLogin()).getPassword()).equals(user.getPassword())){
            session.setAttribute("errorPassword",null);
            session.setAttribute("errorlogin",null);
            session.setAttribute("userIn",true);
            session.setAttribute("userLogin",user.getLogin());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
            requestDispatcher.forward(req, resp);
        } else{
            session.setAttribute("errorPassword",true);
            session.setAttribute("errorLogin",null);
            session.setAttribute("userIn",null);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
            requestDispatcher.forward(req, resp);
        }

    }

}
