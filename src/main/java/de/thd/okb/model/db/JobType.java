package de.thd.okb.model.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author tlang
 * Entity JobType.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "jobtype", schema = "public", indexes = {
        @Index(name = "index_jobtype_code", columnList = "code", unique = true),
        @Index(name = "index_jobtype_nr", columnList = "nr", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
public class JobType implements Serializable, Comparable<JobType> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long jobTypeId;

    @NotNull
    @Size(max = 8)
    @Column(length = 8, nullable = false)
    private String code;

    @NotNull
    @Column(nullable = false)
    @Min(0)
    private int nr;

    @OneToMany(mappedBy = "jobType", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<JobOfferJobType> jobOfferJobTypeSet = new HashSet<>();

    @OneToMany(mappedBy = "jobType", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<JobOfferNewsletterJobType> jobOfferNewsletterJobTypeSet = new HashSet<>();

    @NotNull
    @OneToMany(mappedBy = "jobType", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SearchProfileJobType> searchProfileJobTypeSet = new HashSet<>();

    /**
     * Implemented by Comparable.
     *
     * @param compareJobType the given other instance to be compared to
     * @return int
     */
    @Override
    public int compareTo(@NonNull JobType compareJobType) {
        return this.nr - compareJobType.nr;
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
        JobType jobType = (JobType) o;
        return jobTypeId == jobType.jobTypeId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(jobTypeId);
    }
}
