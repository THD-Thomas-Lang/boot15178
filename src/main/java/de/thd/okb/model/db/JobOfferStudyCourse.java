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
 * Entity JobOfferStudyCourse.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "jobofferstudycourse", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class JobOfferStudyCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long jobOfferStudyCourseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private JobOffer jobOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private StudyCourse studyCourse;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param jobOffer    a given job offer
     * @param studyCourse a given study course
     */
    public JobOfferStudyCourse(@NonNull JobOffer jobOffer, @NonNull StudyCourse studyCourse) {
        this();
        this.jobOffer = jobOffer;
        this.studyCourse = studyCourse;
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
        if (!(o instanceof JobOfferStudyCourse)) return false;
        JobOfferStudyCourse that = (JobOfferStudyCourse) o;
        return Objects.equals(jobOffer, that.jobOffer) &&
                Objects.equals(studyCourse, that.studyCourse);
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(jobOffer, studyCourse);
    }
}
