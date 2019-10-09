package app.entities;

import lombok.*;

import javax.persistence.*;

/** Класс истории пользователя */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User user;
    /** Номер игры пользователя */
    @Column(name = "gameNumber")
    private int gameNumber;

    /** Поптыка угадать число пользователя */
    @Column(name = "textHistory")
    private String textHistory;

    public History( User user,Integer gameNumber,String textHistory) {
        this.gameNumber = gameNumber;
        this.textHistory = textHistory;
        this.user = user;
    }
}
