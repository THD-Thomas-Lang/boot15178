package de.thd.okb.model.dto;

import de.thd.okb.model.db.CompanyContact;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * A JobOfferDistance data transfer object (dto)
 *
 * @author tlang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyContactDto {

    private long companyContactId;

    @NotNull
    @Size(max = 128, min = 2)
    private String description;

    @Size(max = 128)
    @Column(length = 128)
    private String email;

    @Size(max = 128)
    private String phone;

    @Size(max = 255)
    @URL
    private String homepageUrl;

    /**
     * Overloaded constructor.
     * Builds the needed dependencies.
     *
     * @param companyContact a given company contact
     * @param email          a given email
     */
    public CompanyContactDto(CompanyContact companyContact, @NonNull String email) {
        this();
        this.companyContactId = companyContact.getCompanyContactId();
        this.email = email;
        this.description = companyContact.getDescription();
        this.phone = companyContact.getPhone();
        this.homepageUrl = companyContact.getHomepageUrl();
    }

    /**
     * Overloaded constructor.
     * Builds the needed dependencies.
     *
     * @param companyContact a given company contact
     */
    CompanyContactDto(@NonNull CompanyContact companyContact) {
        this(companyContact, companyContact.getEmail());
    }

    /**
     * Overloaded constructor.
     * Builds the needed dependencies.
     *
     * @param email    a given email
     * @param homepage a given homepage
     */
    public CompanyContactDto(@NonNull String email, @NonNull String homepage) {
        this();
        this.email = email;
        this.homepageUrl = homepage;
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
        CompanyContactDto that = (CompanyContactDto) o;
        return companyContactId == that.companyContactId &&
                Objects.equals(email, that.email);
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(companyContactId, email);
    }
}
