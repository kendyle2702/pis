package lqc.com.pis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Nationalized
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Nationalized
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Nationalized
    @Column(name = "email")
    private String email;

    @Nationalized
    @Column(name = "avatar")
    private String avatar;

    @Nationalized
    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Nationalized
    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @ColumnDefault("1")
    @Column(name = "is_active")
    private Boolean isActive;

    @ColumnDefault("0")
    @Column(name = "is_login")
    private Boolean isLogin;

    @ColumnDefault("0")
    @Column(name = "login_attempts")
    private Integer loginAttempts;

    @Column(name = "otp", length = 6)
    private String otp;

}