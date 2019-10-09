package app.entities;


import com.sun.istack.internal.NotNull;
import lombok.*;
import javax.persistence.*;

/** Класс пользователя */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@ToString
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    @NotNull
    private Integer id;

    @Column(length = 50)
    private String login;

    @Column(length = 50)
    private String password;

    /** Поле для хранения числа пользователя */
    @Column(name = "yourNumber")
    private String yourNumber;

    @Transient
    @OneToOne(mappedBy = "users", fetch=FetchType.LAZY)
    private Rating rating;

    public User(String login, String password, String yourNumber) {
        this.login = login;
        this.password = password;
        this.yourNumber = yourNumber;
    }



}
