package de.thd.okb.model.dto;

import de.thd.okb.component.CrossPropertyStringMatcher;
import de.thd.okb.model.profiles.CompanyUserRole;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * A AbstractRoleDto data transfer object (dto)
 *
 * @author tlang
 */
@Getter
@Setter
@CrossPropertyStringMatcher(sourceProperty = "passwordHash", targetProperty = "passwordHashConfirmation")
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUserRoleDto extends AbstractRoleDto {

    @NotNull
    private String passwordHash;

    @NotNull
    private String passwordHashConfirmation;

    private String passwordSalt;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param companyUserRole a given company user role
     */
    public CompanyUserRoleDto(@NonNull CompanyUserRole companyUserRole) {
        super(companyUserRole.getAbstractRoleId(), companyUserRole.getFirstName(), companyUserRole.getLastName(), companyUserRole.getEmail(),
                companyUserRole.getAboutMe());
        this.setPasswordHash(companyUserRole.getPasswordHash());
        this.setPasswordHashConfirmation(companyUserRole.getPasswordHash());
        this.setPasswordSalt(companyUserRole.getPasswordSalt());
    }
}
