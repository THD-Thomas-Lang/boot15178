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
 * Entity SearchProfileStudyCourse.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "searchprofilestudycourse", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class SearchProfileStudyCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long searchProfileStudyCourseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private SearchProfile searchProfile;

    @ManyToOne
    @NotNull
    private StudyCourse studyCourse;

    /**
     * Overloaded constructor.
     *
     * @param searchProfile a given search profile
     * @param studyCourse   a given study course
     */
    SearchProfileStudyCourse(SearchProfile searchProfile, StudyCourse studyCourse) {
        this();
        this.searchProfile = searchProfile;
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
        if (!(o instanceof SearchProfileStudyCourse)) return false;
        SearchProfileStudyCourse that = (SearchProfileStudyCourse) o;
        return Objects.equals(searchProfile, that.searchProfile) &&
                Objects.equals(studyCourse, that.studyCourse);
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {

        return Objects.hash(searchProfile, studyCourse);
    }
}
