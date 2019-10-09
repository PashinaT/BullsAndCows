package app.dao;

import app.config.HibernateSessionFactoryUtil;
import app.entities.History;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

/** Класс для работы с базой истории пользователей*/
public class HistoryDaoImpl implements HistoryDao {
    @Override
    public History findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(History.class, id);
    }

    @Override
    public void save(History history) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(history);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void update(History history) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(history);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(History history) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(history);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<History> findByLogin(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<History> query = session.createQuery("select m from app.entities.History m " +
                "where m.user.id =(select t.id from app.entities.User t where t.login=:login)")
                .setParameter("login", login);
        List<History> results = query.list();
        session.close();
        return results;
    }

    @Override
    public List<History> findAll() {
        List<History> historyList = (List<History>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("From  app.entities.History")
                .list();
        return historyList;
    }
    public int maxGameNumber(String login){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Integer> query = session.createQuery("select max(m.gameNumber) from app.entities.History m " +
                "where m.user.id =(select t.id from app.entities.User t where t.login=:login)")
                .setParameter("login", login);
        List<Integer> results = query.list();
        session.close();
        return results.get(0);
    }
}
