package app.dao;

import app.entities.History;
import java.util.List;

/** Интерфейс для работы с базой истории пользователей */
public interface HistoryDao {
    History findById(int id);
    void save(History history);
    void update(History history);
    void delete(History history);
    List<History> findByLogin(String login);
    List<History> findAll();
}




