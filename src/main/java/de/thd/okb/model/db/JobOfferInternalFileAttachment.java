package de.thd.okb.model.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author tlang
 * Entity JobOfferInternalFileAttachment.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "jobofferinternalfileattachment", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class JobOfferInternalFileAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long jobOfferInternalFileAttachmentId;

    @NotNull
    @Column
    private String file;

    @NotNull
    @Size(max = 250)
    private String filename;

    @NotNull
    @ManyToOne
    private JobOfferInternal jobOfferInternal;

    /**
     * Overloaded constructur.
     * Builds the needed references.
     *
     * @param jobOfferInternal a given job offer internal
     * @param multipartFile    a given multipart file
     */
    JobOfferInternalFileAttachment(@NonNull JobOfferInternal jobOfferInternal, @NonNull MultipartFile multipartFile) {
        this();
        this.jobOfferInternal = jobOfferInternal;
        this.filename = multipartFile.getOriginalFilename();
        this.file = StringUtils.trimAllWhitespace(multipartFile.getOriginalFilename());
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
        JobOfferInternalFileAttachment fileAttachment = (JobOfferInternalFileAttachment) o;
        return jobOfferInternalFileAttachmentId == fileAttachment.jobOfferInternalFileAttachmentId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(jobOfferInternalFileAttachmentId);
    }
}
