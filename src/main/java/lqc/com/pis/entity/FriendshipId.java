package lqc.com.pis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipId implements Serializable {
    private static final long serialVersionUID = -7805796683000160014L;
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "friend_id", nullable = false)
    private Integer friendId;

    @Column(name = "friend_type", nullable = false, length = 50)
    private String friendType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FriendshipId entity = (FriendshipId) o;
        return Objects.equals(this.friendType, entity.friendType) &&
                Objects.equals(this.friendId, entity.friendId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friendType, friendId, userId);
    }

}