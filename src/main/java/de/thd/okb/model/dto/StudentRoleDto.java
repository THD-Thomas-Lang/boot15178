package de.thd.okb.model.dto;

import de.thd.okb.model.profiles.StudentRole;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * A AbstractRoleDto data transfer object (dto)
 *
 * @author tlang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRoleDto extends StaffRoleDto {

    @Max(20)
    @Min(0)
    private int semester = 0;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param studentRole a given student role
     */
    public StudentRoleDto(@NonNull StudentRole studentRole) {
        super(studentRole.getAbstractRoleId(), studentRole.getFirstName(), studentRole.getLastName(), studentRole.getEmail(),
                studentRole.getAboutMe(), studentRole.getEducation(), studentRole.getApprenticeship(), studentRole.getStudycourse(),
                studentRole.getThesis(), studentRole.getInternationalExperience(), studentRole.getLanguageSkills(),
                studentRole.getItSkills(), studentRole.getSpecialSkills(), studentRole.getInterests());
        this.setSemester(studentRole.getSemester());

    }
}
