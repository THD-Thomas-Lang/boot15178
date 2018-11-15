package de.thd.okb.model.db;

import de.thd.okb.model.dto.CompanyDto;
import de.thd.okb.model.dto.ImageDto;
import de.thd.okb.model.other.LatLng;
import de.thd.okb.model.profiles.CompanyUserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author tlang
 * Entity Company.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "company", schema = "public", indexes = {
        @Index(name = "index_company_name", columnList = "name")
})
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Company.address.contacts.companyFileAttachmentSet",
                attributeNodes = {
                        @NamedAttributeNode("address"),
                        @NamedAttributeNode("contacts"),
                        @NamedAttributeNode("companyFileAttachmentSet")
                }),
        @NamedEntityGraph(name = "Company.address",
                attributeNodes = {
                        @NamedAttributeNode("address")
                })
})
@Getter
@Setter
@NoArgsConstructor
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long companyId;

    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    public Address address;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String name;

    @Column
    @Size(max = 255)
    private String homepageUrl;

    @OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<JobOffer> jobOffers = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<CompanyUserRole> users = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("description")
    private Set<CompanyContact> contacts = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SearchProfileCompany> searchProfileCompanySet = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<CompanyFileAttachment> companyFileAttachmentSet = new HashSet<>();

    @Column(nullable = false)
    @NotNull
    private boolean disabled;

    @Column(nullable = false)
    @NotNull
    private boolean partner;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param companyDto a given company dto
     * @param latLng     a given latitude/longitude
     * @param imageDto   a given image dto
     */
    public Company(@NonNull CompanyDto companyDto, LatLng latLng, ImageDto imageDto) {
        this();
        this.address = new Address(companyDto.getAddressDto(), latLng);
        this.name = companyDto.getName();
        this.homepageUrl = companyDto.getHomepageUrl();
        this.disabled = companyDto.isDisabled();
        this.partner = companyDto.isPartner();
        if (imageDto != null) this.companyFileAttachmentSet.add(new CompanyFileAttachment(this, imageDto));
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
        Company company = (Company) o;
        return companyId == company.companyId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(companyId);
    }

    /**
     * Syncs values between a dto and the current instance.
     *
     * @param companyDto a given company dto
     */
    public void syncValues(@NonNull CompanyDto companyDto) {
        this.name = companyDto.getName();
        this.homepageUrl = companyDto.getHomepageUrl();
        this.disabled = companyDto.isDisabled();
        this.partner = companyDto.isPartner();
    }

    /**
     * Syncs some values.
     *
     * @param companyDto a given company dto
     * @param imageDto   a given image dto
     */
    public void syncValues(@NonNull CompanyDto companyDto, ImageDto imageDto) {
        this.syncValues(companyDto);
        if (imageDto != null) {
            this.companyFileAttachmentSet.clear();
            this.companyFileAttachmentSet.add(new CompanyFileAttachment(this, imageDto));
        }
    }
}
