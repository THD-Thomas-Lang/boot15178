package de.thd.okb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A AbstractRoleDto data transfer object (dto)
 *
 * @author tlang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractRoleDto {

    @NotNull
    private long abstractRoleId;

    @NotNull
    @Size(max = 128)
    private String firstName;

    @NotNull
    @Size(max = 128)
    private String lastName;

    @NotNull
    @Size(max = 128)
    @Email
    private String email;

    @Size(max = 4096)
    private String aboutMe;
}
