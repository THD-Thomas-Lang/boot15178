package de.thd.okb.model.db;

import de.thd.okb.model.dto.ImageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author tlang
 * Entity FileAttachment.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "fileattachment", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class FileAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long fileAttachmentId;

    @NotNull
    @Column
    private String file;

    @NotNull
    @Size(max = 250)
    private String filename;

    @NotNull
    @ManyToOne
    private JobOffer jobOffer;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param jobOffer a given job offer
     * @param imageDto a given image dto
     */
    FileAttachment(@NonNull JobOffer jobOffer, @NonNull ImageDto imageDto) {
        this();
        this.jobOffer = jobOffer;
        this.filename = imageDto.getOriginalFileName();
        this.file = imageDto.toString();
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
        FileAttachment fileAttachment = (FileAttachment) o;
        return fileAttachmentId == fileAttachment.fileAttachmentId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(fileAttachmentId);
    }
}
