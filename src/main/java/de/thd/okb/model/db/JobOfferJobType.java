package de.thd.okb.model.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author tlang
 * Entity JobOfferJobType.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "jobofferjobtype", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class JobOfferJobType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long jobOfferJobTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private JobOffer jobOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private JobType jobType;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param jobOffer a given job offer
     * @param jobType  a given job type
     */
    JobOfferJobType(@NonNull JobOffer jobOffer, @NonNull JobType jobType) {
        this.jobOffer = jobOffer;
        this.jobType = jobType;
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
        if (!(o instanceof JobOfferJobType)) return false;
        JobOfferJobType that = (JobOfferJobType) o;
        return Objects.equals(jobOffer, that.jobOffer) &&
                Objects.equals(jobType, that.jobType);
    }

    /**
     * Computes a hash for the current intance
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(jobOffer, jobType);
    }
}
