package de.thd.okb.model.db;

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
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tlang
 * Entity JobOfferNewsletter.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "joboffernewsletter", schema = "public")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "JobOfferNewsletter.jobOfferNewsletterJobTypeSet.jobType",
                attributeNodes = {
                        @NamedAttributeNode(value = "jobOfferNewsletterJobTypeSet", subgraph = "jobOfferNewsletterJobTypeSet")
                }, subgraphs = {
                @NamedSubgraph(
                        name = "jobOfferNewsletterJobTypeSet",
                        attributeNodes = {
                                @NamedAttributeNode("jobType")
                        }
                )
        })
})
@Getter
@Setter
@NoArgsConstructor
public class JobOfferNewsletter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long jobOfferNewsletterId;

    @Size(max = 255)
    @Column
    private String companyName;

    @NotNull
    @Size(max = 512, min = 2)
    @Column(nullable = false)
    private String jobOfferShortDesc;

    @Size(max = 128)
    @Column(length = 128)
    private String jobOfferCity;

    @Size(max = 64)
    @Column(length = 64)
    private String jobOfferIsoCode = Locale.GERMANY.getCountry();

    @Size(max = 128)
    @Column(length = 128)
    private String jobOfferContactDescription;

    @Size(max = 128)
    @Column(length = 128)
    @Email
    private String jobOfferContactEmail;

    @Size(max = 128)
    @Column(length = 128)
    private String jobOfferContactPhone;

    @NotNull
    @Column(nullable = false)
    private boolean repeat = true;

    @NotNull
    @Column(nullable = false, length = 128)
    @Size(max = 128, min = 2)
    private String createdBy;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "jobOfferNewsletter", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<JobOfferNewsletterJobType> jobOfferNewsletterJobTypeSet = new HashSet<>();

    /**
     * Checks whether the current instance is equals to another instance.
     *
     * @param o the other instance
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobOfferNewsletter)) return false;
        JobOfferNewsletter that = (JobOfferNewsletter) o;
        return jobOfferNewsletterId == that.jobOfferNewsletterId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(jobOfferNewsletterId);
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     */
    public JobOfferNewsletter(String login) {
        this();
        this.createdBy = login;
    }

    /**
     * Get all the job type codes for the current offer.
     *
     * @return List of String
     */
    public List<String> printJobTypeCodes() {
        if (jobOfferNewsletterJobTypeSet != null) {
            return jobOfferNewsletterJobTypeSet.stream().map(s -> s.getJobType().getCode()).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * Syncs some values
     *
     * @param jobTypes given job types
     */
    public void syncValues(@NonNull Iterable<JobType> jobTypes) {
        jobTypes.forEach(jobType -> this.jobOfferNewsletterJobTypeSet.add(new JobOfferNewsletterJobType(jobType, this)));

    }

    /**
     * Syncs some values.
     * Does some cleanup work beforehand.
     *
     * @param jobTypes given job types
     */
    public void clearAndSyncValues(@NonNull Iterable<JobType> jobTypes) {
        this.jobOfferNewsletterJobTypeSet.clear();
        this.syncValues(jobTypes);

    }
}
