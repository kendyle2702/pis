package lqc.com.pis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ImageCommentId implements Serializable {
    private static final long serialVersionUID = 1071675992385606263L;
    @Column(name = "comment_id", nullable = false)
    private Integer commentId;

    @Nationalized
    @Column(name = "url", nullable = false)
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ImageCommentId entity = (ImageCommentId) o;
        return Objects.equals(this.commentId, entity.commentId) &&
                Objects.equals(this.url, entity.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, url);
    }

}