package app.entities;


import lombok.*;
import javax.persistence.*;

/**
 * A simple Dao.UserDaoImpl , представляющий рейтинг игр разных пользователей
 *
 * @author Zdornov Maxim
 * @version 1.0
 */

/** Класс рейтинга пользователя */
@Table(name = "rating")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@ToString
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /** Количество игр пользователя */
    @Column(name = "gamesNumber")
    private Integer gamesNumber;
    /** Количество всех шагов пользователя за всё время игры */
    @Column(name = "allSteps")
    private Integer allSteps;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User user;

    public Rating(Integer gamesNumber, Integer allSteps, User user) {
        this.gamesNumber = gamesNumber;
        this.allSteps = allSteps;
        this.user = user;
    }
}