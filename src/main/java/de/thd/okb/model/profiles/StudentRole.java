package de.thd.okb.model.profiles;

import de.thd.okb.model.db.ApplicationUser;
import de.thd.okb.model.db.StudyCourse;
import de.thd.okb.model.dto.PersonDto;
import de.thd.okb.model.dto.StudentRoleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

/**
 * @author tlang
 * Entity AdminRole.
 * Maps the table from the datasource to this type.
 */
@Entity(name = "StudentRole")
@DiscriminatorValue("StudentRole")
@Getter
@Setter
@NoArgsConstructor
public class StudentRole extends StaffRole {

    @Column
    @Max(20)
    @Min(0)
    private int semester = 0;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param personDto       a given person dto
     * @param studyCourse     a given study course
     * @param applicationUser a given application user
     */
    public StudentRole(@NonNull PersonDto personDto, @NonNull StudyCourse studyCourse, @NonNull ApplicationUser applicationUser) {
        this();
        this.setApplicationUser(applicationUser);
        this.setEmail(personDto.getEmail());
        this.setFirstName(personDto.getFirstName());
        this.setLastName(personDto.getLastName());
        this.setStudycourse(studyCourse);
    }

    /**
     * Checks whether the current instance is equals to another instance.
     *
     * @param o the other instance
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof StudentRole && super.equals(o);
    }

    /**
     * Computes a hash code.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    /**
     * syncs values between a db entity and a dto.
     *
     * @param studentRoleDto the given student role dto
     * @param studyCourse    a given study course
     */
    public void syncValues(@NonNull StudentRoleDto studentRoleDto, @NonNull StudyCourse studyCourse) {
        super.syncValues(studentRoleDto.getAbstractRoleId(), studentRoleDto.getFirstName(), studentRoleDto.getLastName(),
                studentRoleDto.getEmail(), studentRoleDto.getAboutMe(), studentRoleDto.getEducation(),
                studentRoleDto.getApprenticeship(), studyCourse, studentRoleDto.getThesis(),
                studentRoleDto.getInternationalExperience(), studentRoleDto.getLanguageSkills(),
                studentRoleDto.getItSkills(), studentRoleDto.getSpecialSkills(),
                studentRoleDto.getInterests());
        this.setSemester(studentRoleDto.getSemester());

    }

}