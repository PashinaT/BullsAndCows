package app.servlets;

import app.dao.HistoryDaoImpl;
import app.dao.RatingDaoImpl;
import app.dao.UserDaoImpl;
import app.entities.Game;
import app.entities.History;
import app.entities.Rating;
import app.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/** Сервлет для игры */
@WebServlet(name = "GameServlet", urlPatterns = "/startGame")
public class GameServlet extends HttpServlet {
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
        else{
            Game game=new Game();
            UserDaoImpl userDao = new UserDaoImpl();
            HistoryDaoImpl historyDao = new HistoryDaoImpl();
            RatingDaoImpl ratingDao = new RatingDaoImpl();
            String login=(String) session.getAttribute("userLogin");
            String answerLine="";
            String winAnswer=" Вы угадали! Новое число уже готово!";

            User user = userDao.findByLogin(login);
            if(user.getYourNumber().equals("0000")){
                String userNumber= game.getNumber();
                user.setYourNumber(userNumber);
                session.setAttribute("userNumber",userNumber);
                userDao.update(user);
            }
            Rating ratingUser = ratingDao.findByLogin(login);

            if (req.getParameter("btn1") != null){
                int userAllSteps=ratingUser.getAllSteps()+1;
                ratingUser.setAllSteps(userAllSteps);
                ratingDao.update(ratingUser);
                String inputNumber = req.getParameter("btn1").trim();
                String userNumber=user.getYourNumber();
                OutputStream outStream = resp.getOutputStream();
                answerLine=game.checkNumber(userNumber, inputNumber);
                outStream.write(answerLine.getBytes("UTF-8"));
                outStream.flush();

                History history = new History(user,ratingUser.getGamesNumber()+1,answerLine);
                historyDao.save(history);

                if(inputNumber.equals(userNumber)){

                    outStream.write(winAnswer.getBytes("UTF-8"));
                    outStream.flush();
                    int userGamesNumber=ratingUser.getGamesNumber()+1;
                    ratingUser.setGamesNumber(userGamesNumber);
                    ratingUser.setAllSteps(userAllSteps);
                    ratingDao.update(ratingUser);
                    user.setYourNumber("0000");
                    userDao.update(user);
                }
                outStream.close();
            }


            if(req.getParameter("inputNumber")!= null){

                int userAllSteps=ratingUser.getAllSteps()+1;
                ratingUser.setAllSteps(userAllSteps);
                ratingDao.update(ratingUser);
                String inputNumber = req.getParameter("inputNumber").trim();
                String userNumber=user.getYourNumber();
                OutputStream outStream = resp.getOutputStream();
                answerLine=game.checkNumber(userNumber, inputNumber);
                outStream.write(answerLine.getBytes("UTF-8"));
                outStream.flush();

                History history = new History(user,ratingUser.getGamesNumber()+1,answerLine);
                historyDao.save(history);

                if(inputNumber.equals(userNumber)){

                    outStream.write(winAnswer.getBytes("UTF-8"));
                    outStream.flush();
                    int userGamesNumber=ratingUser.getGamesNumber()+1;
                    ratingUser.setGamesNumber(userGamesNumber);
                    ratingUser.setAllSteps(userAllSteps);
                    ratingDao.update(ratingUser);
                    user.setYourNumber("0000");
                    userDao.update(user);
                }
                outStream.close();
            }

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/game.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

}
