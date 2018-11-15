package de.thd.okb.model.dto;

import de.thd.okb.component.CrossPropertyStringMatcher;
import de.thd.okb.model.profiles.AlumniRole;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A AbstractRoleDto data transfer object (dto)
 *
 * @author tlang
 */
@Getter
@Setter
@CrossPropertyStringMatcher(sourceProperty = "passwordHash", targetProperty = "passwordHashConfirmation")
@AllArgsConstructor
@NoArgsConstructor
public class AlumniRoleDto extends StaffRoleDto {

    @NotNull
    private String passwordHash;

    @NotNull
    private String passwordHashConfirmation;

    @NotNull
    @Size(min = 2)
    private String username;

    private String passwordSalt;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param alumniRole a given alumin role
     */
    public AlumniRoleDto(@NonNull AlumniRole alumniRole) {
        super(alumniRole.getAbstractRoleId(), alumniRole.getFirstName(), alumniRole.getLastName(), alumniRole.getEmail(),
                alumniRole.getAboutMe(), alumniRole.getEducation(), alumniRole.getApprenticeship(), alumniRole.getStudycourse(), alumniRole.getThesis(),
                alumniRole.getInternationalExperience(), alumniRole.getLanguageSkills(), alumniRole.getItSkills(), alumniRole.getSpecialSkills(),
                alumniRole.getInterests());
        this.setPasswordHash(alumniRole.getPasswordHash());
        this.setPasswordHashConfirmation(alumniRole.getPasswordHash());
        this.setPasswordSalt(alumniRole.getPasswordSalt());
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param hasReadPrivacyDisclaimer if the privacy disclaimer has been read
     */
    public AlumniRoleDto(boolean hasReadPrivacyDisclaimer) {
        this();
        this.setHasAcceptedPrivacyDisclaimer(hasReadPrivacyDisclaimer);

    }
}
