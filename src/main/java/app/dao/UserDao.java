package app.dao;

import app.entities.User;
import java.util.List;

/** Интерфейс для работы с базой пользователей */
public interface UserDao {
    User findById(int id) ;
    List<User> findAllByID(int id);
    User findByLogin(String login);
    void save(User user);
    void delete(User user);
    void update(User user);
    List<User> findAll();
}
