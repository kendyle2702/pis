package lqc.com.pis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "friendship")
public class Friendship {
    @EmbeddedId
    private FriendshipId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("friendId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "friend_id", nullable = false)
    private User friend;

    @ColumnDefault("0")
    @Column(name = "is_friend")
    private Boolean isFriend;

    @ColumnDefault("0")
    @Column(name = "is_block")
    private Boolean isBlock;

}