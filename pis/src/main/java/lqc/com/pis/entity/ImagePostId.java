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
public class ImagePostId implements Serializable {
    private static final long serialVersionUID = 8431962091356932058L;
    @Column(name = "post_id", nullable = false)
    private Integer postId;

    @Nationalized
    @Column(name = "url", nullable = false)
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ImagePostId entity = (ImagePostId) o;
        return Objects.equals(this.postId, entity.postId) &&
                Objects.equals(this.url, entity.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, url);
    }

}