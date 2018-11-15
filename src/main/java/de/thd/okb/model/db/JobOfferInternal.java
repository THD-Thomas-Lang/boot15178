package de.thd.okb.model.db;

import de.thd.okb.model.dto.InternalOfferDto;
import de.thd.okb.model.other.JobTypeEnum;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author tlang
 * Entity JobOfferInternal.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "jobofferinternal", schema = "public", indexes = {
        @Index(name = "index_jobofferinternal_area", columnList = "area")
})
@NamedEntityGraphs({
        @NamedEntityGraph(name = "JobOfferInternal.studyCourses.companyContact",
                attributeNodes = {
                        @NamedAttributeNode("companyContact"),
                        @NamedAttributeNode(value = "jobOfferInternalStudyCourseSet", subgraph = "jobOfferInternalStudyCourseSet")
                }, subgraphs = {
                @NamedSubgraph(
                        name = "jobOfferInternalStudyCourseSet",
                        attributeNodes = {
                                @NamedAttributeNode("studyCourse")
                        }
                )
        }),
})
@Getter
@Setter
@NoArgsConstructor
public class JobOfferInternal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long jobOfferInternalId;

    @NotNull
    @Size(max = 128)
    @Column(nullable = false)
    private String area;

    @NotNull
    @Size(max = 1024)
    @Column(nullable = false)
    private String description;

    @NotNull
    @Size(max = 64)
    @Column(nullable = false)
    private String occupationScope;

    @NotNull
    @Column(nullable = false)
    private boolean useHqAddress = true;

    @Column(nullable = false)
    @NotNull
    private boolean cv = false;

    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    public Address address;

    @Column(nullable = false)
    @NotNull
    private boolean coverletter = false;

    @Column(nullable = false)
    @NotNull
    private boolean gradesheet = false;

    @Size(max = 64)
    @Column
    private String otherDocuments;

    @NotNull
    @Column(nullable = false)
    private LocalDate validFromDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate validUntilDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @NotNull
    @Column(nullable = false, length = 128)
    @Size(max = 128)
    private String updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private CompanyContact companyContact;

    @Column(nullable = false)
    @Enumerated
    @NotNull
    private JobTypeEnum jobType;

    @OneToMany(mappedBy = "jobOfferInternal", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<JobOfferInternalFileAttachment> jobOfferInternalFileAttachmentSet = new HashSet<>();

    @OneToMany(mappedBy = "jobOfferInternal", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<JobOfferInternalStudyCourse> jobOfferInternalStudyCourseSet = new HashSet<>();

    @Column
    private LocalDateTime updatedAt;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param internalOfferDto a given internal offer dto
     * @param company          a given company
     * @param address          a given address
     * @param contactByEmail   a given email
     * @param login            a given user login
     * @param studyCourses     a given study course list
     */
    public JobOfferInternal(@NonNull InternalOfferDto internalOfferDto,
                            @NonNull Company company, @NonNull Address address,
                            CompanyContact contactByEmail, String login,
                            @NonNull Iterable<StudyCourse> studyCourses,
                            @NonNull JobTypeEnum jobTypeEnum) {
        this.jobOfferInternalId = internalOfferDto.getInternalOfferId();
        this.area = internalOfferDto.getArea();
        this.description = internalOfferDto.getDescription();
        this.validFromDate = internalOfferDto.getValidFrom();
        this.validUntilDate = internalOfferDto.getValidUntil();
        this.occupationScope = internalOfferDto.getOccupationScope();
        this.cv = internalOfferDto.isCv();
        this.coverletter = internalOfferDto.isCoverletter();
        this.gradesheet = internalOfferDto.isGradesheet();
        this.otherDocuments = internalOfferDto.getOtherDocuments();
        this.updatedBy = login;
        this.company = company;
        this.address = address;
        this.companyContact = (contactByEmail != null) ? contactByEmail : new CompanyContact(internalOfferDto.getCompanyContactDto(),
                company, address, this);
        studyCourses.forEach((studyCourse -> this.jobOfferInternalStudyCourseSet.add(new JobOfferInternalStudyCourse(this, studyCourse))));
        if (internalOfferDto.getMultipartFile() != null)
            this.jobOfferInternalFileAttachmentSet.add(new JobOfferInternalFileAttachment(this, internalOfferDto.getMultipartFile()));
        this.jobType = jobTypeEnum;
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
        JobOfferInternal jobOffer = (JobOfferInternal) o;
        return jobOfferInternalId == jobOffer.jobOfferInternalId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(jobOfferInternalId);
    }

    /**
     * Printable version of the current instance.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "JobOfferInternal{" +
                "jobOfferInternalId=" + jobOfferInternalId +
                ", area='" + area + '\'' +
                '}';
    }

    /**
     * Syncs values for editing purpose
     *
     * @param internalOfferDto a given internal offer dto
     * @param company          a given company
     * @param address          a given address
     * @param contactByEmail   a given contact by email
     * @param login            a given login
     * @param studyCourses     given study courses
     */
    public void syncValues(@NonNull InternalOfferDto internalOfferDto,
                           @NonNull Company company,
                           @NonNull Address address,
                           CompanyContact contactByEmail,
                           @NonNull String login,
                           @NonNull Iterable<StudyCourse> studyCourses) {

        this.jobOfferInternalId = internalOfferDto.getInternalOfferId();
        this.area = internalOfferDto.getArea();
        this.description = internalOfferDto.getDescription();
        this.validFromDate = internalOfferDto.getValidFrom();
        this.validUntilDate = internalOfferDto.getValidUntil();
        this.occupationScope = internalOfferDto.getOccupationScope();
        this.occupationScope = internalOfferDto.getOccupationScope();
        this.cv = internalOfferDto.isCv();
        this.coverletter = internalOfferDto.isCoverletter();
        this.gradesheet = internalOfferDto.isGradesheet();
        this.otherDocuments = internalOfferDto.getOtherDocuments();
        this.updatedBy = login;
        this.updatedAt = LocalDateTime.now();
        this.company = company;
        this.address = address;
        this.companyContact = contactByEmail == null ? new CompanyContact(internalOfferDto.getCompanyContactDto(), company, address) : contactByEmail;
        this.jobOfferInternalStudyCourseSet.clear();
        studyCourses.forEach((studyCourse -> this.jobOfferInternalStudyCourseSet.add(new JobOfferInternalStudyCourse(this, studyCourse))));
        this.jobType = internalOfferDto.getJobType();

    }

}
