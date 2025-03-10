package lqc.com.pis.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Builder
@Table(name = "image_post")
@NoArgsConstructor
@AllArgsConstructor
public class ImagePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_post_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Nationalized
    @Column(name = "url")
    private String url;

}