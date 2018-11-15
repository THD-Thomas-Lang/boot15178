package de.thd.okb.model.dto;

import de.thd.okb.model.profiles.CompanyUserRole;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * A CompanyUserDto data transfer object (dto)
 *
 * @author tlang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUserDto {

    @NotNull
    @Valid
    private CompanyUserRoleDto companyUserRoleDto;

    @NotNull
    private long companyId;

    /**
     * Overloaded constructor.
     *
     * @param companyId a given company id
     */
    public CompanyUserDto(long companyId) {
        this();
        this.companyUserRoleDto = new CompanyUserRoleDto();
        this.companyId = companyId;
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param companyUserRole a given company user role
     * @param companyId       a given company id
     */
    public CompanyUserDto(@NonNull CompanyUserRole companyUserRole, long companyId) {
        this(companyId);
        this.companyUserRoleDto = new CompanyUserRoleDto(companyUserRole);
    }
}
