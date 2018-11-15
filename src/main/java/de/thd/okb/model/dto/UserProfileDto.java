package de.thd.okb.model.dto;

import de.thd.okb.model.profiles.AbstractRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * UserProfileDto Type.
 *
 * @author tlang
 */
@Getter
@Setter
@NoArgsConstructor
public class UserProfileDto {

    private long userProfileId;

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

    @NotNull
    @Size(max = 128)
    private String username;

    @AssertTrue
    private boolean hasAcceptedPrivacyDisclaimer = false;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param abstractRole a given abstract role.
     */
    public UserProfileDto(AbstractRole abstractRole) {
        this();
        Assert.notNull(abstractRole.getApplicationUser(), "There has to be a given application user!");
        this.userProfileId = abstractRole.getAbstractRoleId();
        this.firstName = abstractRole.getFirstName();
        this.lastName = abstractRole.getLastName();
        this.email = abstractRole.getEmail();
        this.username = abstractRole.getApplicationUser().getUsername();
    }
}
