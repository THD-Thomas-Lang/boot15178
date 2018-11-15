package de.thd.okb.model.db;

import de.thd.okb.model.dto.CompanyContactDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author tlang
 * Entity CompanyContact.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "companycontact", schema = "public", indexes = {
        @Index(name = "index_companycontact_description", columnList = "description"),
        @Index(name = "index_companycontact_email", columnList = "email")
})
@Getter
@Setter
@NoArgsConstructor
public class CompanyContact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long companyContactId;

    @NotNull
    @Size(max = 128)
    @Column(nullable = false, length = 128)
    private String description;

    @Size(max = 128)
    @Column(length = 128)
    @Email
    private String email;

    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    public Address address;

    @Size(max = 128)
    @Column(length = 128)
    private String phone;

    @Column(nullable = false)
    @NotNull
    private boolean useHqAddress = true;

    @NotNull
    @ManyToOne
    private Company company;

    @Column
    @Size(max = 255)
    private String homepageUrl;

    @OneToMany(mappedBy = "companyContact", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<JobOffer> jobOffers = new HashSet<>();

    @OneToMany(mappedBy = "companyContact", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<JobOfferInternal> jobOffersInternal = new HashSet<>();

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param companyContactDto a given contact dto
     * @param company           a given company
     * @param address           a given address
     */
    public CompanyContact(@NonNull CompanyContactDto companyContactDto, @NonNull Company company,
                          @NonNull Address address) {
        this();
        this.company = company;
        this.homepageUrl = companyContactDto.getHomepageUrl();
        this.phone = companyContactDto.getPhone();
        this.description = companyContactDto.getDescription();
        this.email = companyContactDto.getEmail();
        this.address = address;
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param companyContactDto a given company contact dto
     * @param company           a given company
     * @param address           a given address
     * @param jobOfferInternal  a given job offer
     */
    public CompanyContact(@NonNull CompanyContactDto companyContactDto, @NonNull Company company,
                          @NonNull Address address, @NonNull JobOfferInternal jobOfferInternal) {

        this(companyContactDto, company, address);
        this.jobOffersInternal.add(jobOfferInternal);
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param companyContactDto a given company contact dto
     * @param company           a given company
     * @param address           a given address
     * @param jobOffer          a given job offer
     */
    public CompanyContact(@NonNull CompanyContactDto companyContactDto, @NonNull Company company,
                          @NonNull Address address, @NonNull JobOffer jobOffer) {
        this(companyContactDto, company, address);
        this.jobOffers.add(jobOffer);
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
        CompanyContact companyContact = (CompanyContact) o;
        return companyContactId == companyContact.companyContactId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(companyContactId);
    }

    /**
     * Syncs values between a dto and the current instance.
     *
     * @param companyContactDto the given dto
     */
    public void syncValues(@NonNull CompanyContactDto companyContactDto) {
        this.description = companyContactDto.getDescription();
        this.email = companyContactDto.getEmail();
        this.homepageUrl = companyContactDto.getHomepageUrl();
        this.phone = companyContactDto.getPhone();
    }

    /**
     * Prints a readable version of the current instance.
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s; %s; %s", description, email, phone);
    }
}
