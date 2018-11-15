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
 * Entity SearchProfileJobType.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "searchprofilejobtype", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class SearchProfileJobType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long searchProfileJobTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private SearchProfile searchProfile;

    @ManyToOne
    @NotNull
    private JobType jobType;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param searchProfile a given search profile
     * @param jobType       a given job type
     */
    SearchProfileJobType(SearchProfile searchProfile, JobType jobType) {
        this();
        this.searchProfile = searchProfile;
        this.jobType = jobType;
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
        if (!(o instanceof SearchProfileJobType)) return false;
        SearchProfileJobType that = (SearchProfileJobType) o;
        return Objects.equals(searchProfile, that.searchProfile) &&
                Objects.equals(jobType, that.jobType);
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {

        return Objects.hash(searchProfile, jobType);
    }
}
