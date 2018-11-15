package de.thd.okb.model.db;

import de.thd.okb.model.dto.ImageDto;
import de.thd.okb.model.dto.JobOfferDto;
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
 * Entity JobOffer.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "joboffer", schema = "public", indexes = {
        @Index(name = "index_joboffer_shortdesc", columnList = "shortDesc"),
        @Index(name = "index_joboffer_updatedby", columnList = "updatedBy")
}
)
@NamedEntityGraphs({
        @NamedEntityGraph(name = "JobOffer.jobTypes.studyCourses",
                attributeNodes = {
                        @NamedAttributeNode("jobOfferJobTypeSet"),
                        @NamedAttributeNode("jobOfferStudyCourseSet")
                }),
        @NamedEntityGraph(name = "JobOffer.jobTypes.studyCourses.company.companyContact",
                attributeNodes = {
                        @NamedAttributeNode("company"),
                        @NamedAttributeNode("companyContact"),
                        @NamedAttributeNode(value = "jobOfferStudyCourseSet", subgraph = "jobOfferStudyCourseSet"),
                        @NamedAttributeNode(value = "jobOfferJobTypeSet", subgraph = "jobOfferJobTypeSet")
                }, subgraphs = {
                @NamedSubgraph(
                        name = "jobOfferStudyCourseSet",
                        attributeNodes = {
                                @NamedAttributeNode("studyCourse")
                        }
                ),
                @NamedSubgraph(
                        name = "jobOfferJobTypeSet",
                        attributeNodes = {
                                @NamedAttributeNode("jobType")
                        }
                )
        }),
        @NamedEntityGraph(name = "JobOffer.company",
                attributeNodes = {
                        @NamedAttributeNode("company")
                }),
        @NamedEntityGraph(name = "JobOffer.company.address",
                attributeNodes = {
                        @NamedAttributeNode("company"),
                        @NamedAttributeNode("address")
                }),
        @NamedEntityGraph(name = "JobOffer.studyCourses.companyContact",
                attributeNodes = {
                        @NamedAttributeNode("companyContact"),
                        @NamedAttributeNode(value = "jobOfferStudyCourseSet", subgraph = "jobOfferStudyCourseSet")
                }, subgraphs = {
                @NamedSubgraph(
                        name = "jobOfferStudyCourseSet",
                        attributeNodes = {
                                @NamedAttributeNode("studyCourse")
                        }
                )
        }),
})
@Getter
@Setter
@NoArgsConstructor
public class JobOffer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long jobOfferId;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false)
    private String shortDesc;

    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    public Address address;

    @NotNull
    @Column(nullable = false)
    private boolean useHqAddress;

    @NotNull
    @Column(nullable = false)
    private boolean isPublic = true;

    @NotNull
    @Column(nullable = false)
    private LocalDate validFromDate;

    @NotNull
    @Column(nullable = false)
    private LocalDate validUntilDate;

    @NotNull
    @Column(nullable = false)
    private long validForMonths = 6;

    @NotNull
    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @NotNull
    @Column(length = 8192, nullable = false)
    @Size(max = 8192)
    private String description;

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

    @OneToMany(mappedBy = "jobOffer", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<JobOfferJobType> jobOfferJobTypeSet = new HashSet<>();

    @OneToMany(mappedBy = "jobOffer", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<FileAttachment> fileAttachmentSet = new HashSet<>();

    @OneToMany(mappedBy = "jobOffer", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<JobOfferStudyCourse> jobOfferStudyCourseSet = new HashSet<>();

    @Column
    private LocalDateTime updatedAt;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param jobOfferDto    a given job offer dto
     * @param studyCourses   a given study course list
     * @param jobTypes       a given job types list
     * @param company        a given company
     * @param companyContact a given company contact
     * @param username       a given user name
     */
    private JobOffer(@NonNull JobOfferDto jobOfferDto, @NonNull Iterable<StudyCourse> studyCourses,
                     @NonNull Iterable<JobType> jobTypes, @NonNull Company company,
                     @NonNull CompanyContact companyContact, @NonNull String username) {
        this();
        this.jobOfferId = jobOfferDto.getJobOfferId();
        this.shortDesc = jobOfferDto.getShortDesc();
        this.description = jobOfferDto.getDescription();
        this.validFromDate = jobOfferDto.getValidFrom();
        this.validForMonths = jobOfferDto.getValidForMonths();
        this.validUntilDate = this.validFromDate.plusMonths(this.validForMonths);
        this.useHqAddress = true;
        this.updatedBy = username;
        this.company = company;
        this.address = company.getAddress();
        this.companyContact = companyContact;
        this.jobOfferStudyCourseSet.clear();
        studyCourses.forEach((studyCourse -> this.jobOfferStudyCourseSet.add(new JobOfferStudyCourse(this, studyCourse))));

        jobTypes.forEach((jobType -> this.jobOfferJobTypeSet.add(new JobOfferJobType(this, jobType))));

    }

    /**
     * Overloaded constructor.
     * Builds the needed references
     *
     * @param jobOfferDto    a given job offer dto
     * @param studyCourses   a given list of study courses
     * @param jobTypes       a given list of job types
     * @param company        a given company
     * @param companyContact a given company contact
     * @param username       a given user name
     * @param address        a given address
     */
    public JobOffer(@NonNull JobOfferDto jobOfferDto, @NonNull Iterable<StudyCourse> studyCourses, @NonNull Iterable<JobType> jobTypes,
                    @NonNull Company company, @NonNull CompanyContact companyContact, @NonNull String username,
                    @NonNull Address address, ImageDto imageDto) {
        this(jobOfferDto, studyCourses, jobTypes, company, companyContact, username);
        this.address = address;
        if (imageDto != null) this.fileAttachmentSet.add(new FileAttachment(this, imageDto));

    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param jobOfferDto    a given job offer dto
     * @param studyCourses   given study courses
     * @param jobTypes       given job types
     * @param company        a given company
     * @param companyContact a given company contact
     * @param username       a given user name
     * @param imageDto       a given image dto
     */
    public JobOffer(JobOfferDto jobOfferDto, Iterable<StudyCourse> studyCourses, Iterable<JobType> jobTypes,
                    Company company, CompanyContact companyContact, String username, ImageDto imageDto) {
        this(jobOfferDto, studyCourses, jobTypes, company, companyContact, username);
        if (imageDto != null) this.fileAttachmentSet.add(new FileAttachment(this, imageDto));
    }

//

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
        JobOffer jobOffer = (JobOffer) o;
        return jobOfferId == jobOffer.jobOfferId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(jobOfferId);
    }

    /**
     * Printable version of the current instance.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "JobOffer{" +
                "jobOfferId=" + jobOfferId +
                ", shortDesc='" + shortDesc + '\'' +
                '}';
    }

    /**
     * Syncs some values
     *
     * @param jobOfferDto        a given job offer dto
     * @param username           a given username
     * @param company            a given company
     * @param companyContact     a given company contact
     * @param jobTypesByCode     given job types
     * @param studyCoursesByCode given study courses
     * @param imageDto           a given image dto
     */
    public void syncValues(@NonNull JobOfferDto jobOfferDto, @NonNull String username, @NonNull Company company,
                           @NonNull CompanyContact companyContact, @NonNull Iterable<JobType> jobTypesByCode,
                           @NonNull Iterable<StudyCourse> studyCoursesByCode, ImageDto imageDto) {
        this.jobOfferId = jobOfferDto.getJobOfferId();
        this.shortDesc = jobOfferDto.getShortDesc();
        this.useHqAddress = jobOfferDto.isUseHqAddress();
        this.isPublic = jobOfferDto.isOfferPublic();
        this.validFromDate = jobOfferDto.getValidFrom();
        this.validUntilDate = this.validFromDate.plusMonths(jobOfferDto.getValidForMonths());
        this.validForMonths = jobOfferDto.getValidForMonths();
        this.description = jobOfferDto.getDescription();
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = username;
        this.company = company;
        this.companyContact = companyContact;
        this.jobOfferJobTypeSet.forEach(jobOfferJobType -> jobOfferJobType.setJobOffer(null));
        this.jobOfferJobTypeSet.clear();
        this.jobOfferStudyCourseSet.forEach(jobOfferStudyCourse -> jobOfferStudyCourse.setJobOffer(null));
        this.jobOfferStudyCourseSet.clear();
        jobTypesByCode.forEach(jobType -> this.jobOfferJobTypeSet.add(new JobOfferJobType(this, jobType)));
        studyCoursesByCode.forEach(studyCourse -> this.jobOfferStudyCourseSet.add(new JobOfferStudyCourse(this, studyCourse)));
        if (imageDto != null) {
            this.fileAttachmentSet.clear();
            this.fileAttachmentSet.add(new FileAttachment(this, imageDto));
        }
    }
}
