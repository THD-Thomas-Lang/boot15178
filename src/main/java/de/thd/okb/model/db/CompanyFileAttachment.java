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
 * Entity CompanyFileAttachment.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "companyfileattachment", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class CompanyFileAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long companyFileAttachmentId;

    @NotNull
    @Column
    private String file;

    @NotNull
    @Size(max = 250)
    private String filename;

    @NotNull
    @ManyToOne
    private Company company;

    /**
     * Overloaded constructur.
     * Builds the needed references.
     *
     * @param company  a given job company
     * @param imageDto a given image dto
     */
    CompanyFileAttachment(@NotNull Company company, @NonNull ImageDto imageDto) {
        this();
        this.company = company;
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
        CompanyFileAttachment fileAttachment = (CompanyFileAttachment) o;
        return companyFileAttachmentId == fileAttachment.companyFileAttachmentId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(companyFileAttachmentId);
    }
}
