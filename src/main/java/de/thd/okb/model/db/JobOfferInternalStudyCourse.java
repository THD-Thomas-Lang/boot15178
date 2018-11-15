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
 * Entity JobOfferInternalStudyCourse.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "jobofferinternalstudycourse", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class JobOfferInternalStudyCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long jobOfferInternalStudyCourseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private JobOfferInternal jobOfferInternal;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private StudyCourse studyCourse;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param jobOfferInternal a given internal job offer
     * @param studyCourse      a given study course
     */
    public JobOfferInternalStudyCourse(@NonNull JobOfferInternal jobOfferInternal, @NonNull StudyCourse studyCourse) {
        this();
        this.jobOfferInternal = jobOfferInternal;
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
        if (!(o instanceof JobOfferInternalStudyCourse)) return false;
        JobOfferInternalStudyCourse that = (JobOfferInternalStudyCourse) o;
        return Objects.equals(jobOfferInternal, that.jobOfferInternal) &&
                Objects.equals(studyCourse, that.studyCourse);
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(jobOfferInternal, studyCourse);
    }
}
