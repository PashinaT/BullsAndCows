package app.dao;


import app.config.HibernateSessionFactoryUtil;
import app.entities.Rating;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;


/** Класс для работы с базой рейтинга пользователей */
public class RatingDaoImpl implements RatingDao {
    @Override
    public Rating findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Rating.class, id);

    }
    @Override
    public void save(Rating rating) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(rating);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Rating rating) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(rating);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Rating rating) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(rating);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Rating findByLogin(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query<Rating> query = session.createQuery("select m from app.entities.Rating m " +
                "where m.user.id =(select t.id from app.entities.User t where t.login=:login)")
                .setParameter("login", login);
        List<Rating> results = query.list();
        session.close();
        return results.get(0);
    }

    @Override
    public List<Rating> findAll() {
        List<Rating> ratingList = (List<Rating>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("From  app.entities.Rating")
                .list();
        return ratingList;
    }

}
