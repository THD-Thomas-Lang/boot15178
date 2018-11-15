package de.thd.okb.model.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author tlang
 * Entity StudyCourse.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "studycourse", schema = "public", indexes = {
        @Index(name = "index_studycourse_code", columnList = "code", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
public class StudyCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long studyCourseId;

    @NotNull
    @Size(max = 250)
    private String code;

    @NotNull
    @OneToMany(mappedBy = "studyCourse", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SearchProfileStudyCourse> searchProfileStudyCourseSet = new HashSet<>();

    @NotNull
    @OneToMany(mappedBy = "studyCourse", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<JobOfferStudyCourse> jobOfferStudyCourseSet = new HashSet<>();

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
        StudyCourse studyCourse = (StudyCourse) o;
        return studyCourseId == studyCourse.studyCourseId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(studyCourseId);
    }
}