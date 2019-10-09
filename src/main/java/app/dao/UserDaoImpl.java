package app.dao;

import app.config.HibernateSessionFactoryUtil;
import app.entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

/** Класс для работы с базой пользователей */
public class UserDaoImpl implements UserDao {
    @Override
    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }


    @Override
    public User findByLogin(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<User> query = session.createQuery("select m from app.entities.User m " +
                "where m.login=:login")
                .setParameter("login" , login);
        List<User> result = query.list();
        if (result.size()==0){
            return null;
        }
        session.close();
        return result.get(0);
    }


    @Override
    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> findAll() {
        List<User> userList = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("From  app.entities.User")
                .list();
        return userList;
    }

    @Override
    public List<User> findAllByID(int id) {
        List<User> userList = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("From  app.entities.User")
                .list();
        return userList;
    }
}
