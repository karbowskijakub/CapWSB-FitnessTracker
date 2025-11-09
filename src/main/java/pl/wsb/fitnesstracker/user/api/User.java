package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "first_name",nullable = false, unique = true)
    private String firstName;

    @Column(name = "last_name",nullable = false, unique = true)
    private String lastName;

    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthday,
            final String email) {

        this.birthday = birthday;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

}

