package app.dao;

import app.entities.Rating;
import java.util.List;

/** Интерфейс для работы с базой рейтинга пользователей */
public interface RatingDao {
    Rating findById(int id);
    void save(Rating rating);
    void update(Rating rating);
    void delete(Rating rating);
    Rating findByLogin(String login);
    List<Rating> findAll();
}
