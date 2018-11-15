package de.thd.okb.model.dto;

import de.thd.okb.model.db.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A JobOfferDistance data transfer object (dto)
 *
 * @author tlang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    @NotNull
    private long postId;

    @NotNull
    @Size(max = 255)
    private String subject;

    private MultipartFile multipartFile;

    private String image;

    @Size(max = 16384)
    @NotNull
    private String text;

    @NotNull
    private boolean isPublic = true;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param post a given post
     */
    public PostDto(Post post) {
        this();
        this.postId = post.getPostId();
        this.subject = post.getSubject();
        this.text = post.getText();
        this.isPublic = post.isShowPublic();
        this.image = post.getImage();
    }
}
