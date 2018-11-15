package de.thd.okb.model.db;

import de.thd.okb.model.dto.ImageDto;
import de.thd.okb.model.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author tlang
 * Entity Post.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "post", schema = "public", indexes = {
        @Index(name = "index_post_subject", columnList = "subject")
})
@Getter
@Setter
@NoArgsConstructor
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long postId;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String subject;

    @Column
    private String image;

    @Column(length = 16384, nullable = false)
    @Size(max = 16384)
    @NotNull
    private String text;

    @Column(nullable = false)
    @NotNull
    private boolean showPublic = true;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param postDto  a given post dto
     * @param imageDto a given file image dto
     */
    public Post(@NonNull PostDto postDto, @NonNull ImageDto imageDto) {
        this(postDto);
        Assert.isTrue(!imageDto.getFileName().isEmpty(), "A filename cannot be empty. Processing stopped!");
        Assert.isTrue(!imageDto.getMimeType().isEmpty(), "A filename cannot be empty. Processing stopped!");
        this.image = imageDto.toString();

    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param postDto a given post dto
     */
    public Post(@NonNull PostDto postDto) {
        this();
        this.subject = postDto.getSubject();
        this.text = postDto.getText();
        this.showPublic = postDto.isPublic();
        this.image = "";

    }

    /**
     * Checks whether the current instance is equals to another instance.
     *
     * @param o the other instance
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postId == post.postId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }

    /**
     * Syncs values between the current instance and a dto.
     *
     * @param postDto a given dto
     */
    public void syncValues(@NonNull PostDto postDto) {
        this.subject = postDto.getSubject();
        this.showPublic = postDto.isPublic();
        this.text = postDto.getText();
        this.image = "";
    }
}
