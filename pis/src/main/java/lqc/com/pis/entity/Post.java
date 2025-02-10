package lqc.com.pis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "post_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Nationalized
    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Nationalized
    @Lob
    @Column(name = "content")
    private String content;

    @Nationalized
    @Column(name = "mode", nullable = false, length = 50)
    private String mode;

    @ColumnDefault("getdate()")
    @Column(name = "create_at")
    private Instant createAt;

}