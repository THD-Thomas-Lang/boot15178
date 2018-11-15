package de.thd.okb.model.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author tlang
 * Entity JobOfferNewsletterJobType.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "joboffernewsletterjobtype", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class JobOfferNewsletterJobType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long jobOfferNewsletterJobTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private JobOfferNewsletter jobOfferNewsletter;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private JobType jobType;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param jobType            a given job type
     * @param jobOfferNewsletter a given job offer newsletter
     */
    JobOfferNewsletterJobType(JobType jobType, JobOfferNewsletter jobOfferNewsletter) {
        this.jobType = jobType;
        this.jobOfferNewsletter = jobOfferNewsletter;
    }

    /**
     * Is the current instance equals the given instance.
     *
     * @param o a given instance
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobOfferNewsletterJobType)) return false;
        JobOfferNewsletterJobType that = (JobOfferNewsletterJobType) o;
        return Objects.equals(jobOfferNewsletter, that.jobOfferNewsletter) &&
                Objects.equals(jobType, that.jobType);
    }

    /**
     * Computes a hash for the current intance
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(jobOfferNewsletter, jobType);
    }
}
