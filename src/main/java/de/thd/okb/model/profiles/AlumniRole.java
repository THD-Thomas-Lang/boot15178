package de.thd.okb.model.profiles;

import de.thd.okb.model.db.ApplicationUser;
import de.thd.okb.model.db.StudyCourse;
import de.thd.okb.model.dto.AlumniRoleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author tlang
 * Entity StaffRole.
 * Maps the table from the datasource to this type.
 */
@Entity(name = "AlumniRole")
@DiscriminatorValue("AlumniRole")
@Getter
@Setter
@NoArgsConstructor
public class AlumniRole extends StaffRole {

    @NotNull
    @Column(nullable = false)
    private String passwordHash;

    @NotNull
    @Column(nullable = false)
    private String oldPasswordHash;

    @NotNull
    @Column(nullable = false)
    private String passwordSalt;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param applicationUser a given application user
     * @param alumniRoleDto   a given alumni role dto
     * @param studyCourse     a given study course
     * @param passwordHash    a given password hash
     * @param passwordSalt    a given passoword salt
     */
    public AlumniRole(@NonNull ApplicationUser applicationUser, @NonNull AlumniRoleDto alumniRoleDto, @NonNull StudyCourse studyCourse, @NonNull String passwordHash,
                      @NonNull String passwordSalt, @NonNull String oldPasswordHash) {
        this();
        this.syncValues(alumniRoleDto, studyCourse, passwordHash, passwordSalt, oldPasswordHash);
        this.setApplicationUser(applicationUser);

    }

    /**
     * Checks whether the current instance is equals to another instance.
     *
     * @param o the other instance
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof AlumniRole && super.equals(o);
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
     * @param alumniRoleDto   the given alumniRole dto
     * @param studyCourse     a given study course
     * @param passwordHash    a given password hash
     * @param passwordSalt    a given password salt
     * @param oldPasswordHash a given old password hash
     */
    public void syncValues(@NonNull AlumniRoleDto alumniRoleDto, @NonNull StudyCourse studyCourse,
                           @NonNull String passwordHash,
                           @NonNull String passwordSalt,
                           @NonNull String oldPasswordHash) {
        super.syncValues(alumniRoleDto.getAbstractRoleId(), alumniRoleDto.getFirstName(), alumniRoleDto.getLastName(),
                alumniRoleDto.getEmail(), alumniRoleDto.getAboutMe(), alumniRoleDto.getEducation(),
                alumniRoleDto.getApprenticeship(), studyCourse, alumniRoleDto.getThesis(),
                alumniRoleDto.getInternationalExperience(), alumniRoleDto.getLanguageSkills(),
                alumniRoleDto.getItSkills(), alumniRoleDto.getSpecialSkills(),
                alumniRoleDto.getInterests());
        Assert.isTrue(!passwordHash.isEmpty(), "Empty password hash detected!");
        Assert.isTrue(!passwordSalt.isEmpty(), "Empty password salt detected!");
        this.setPasswordHash(passwordHash);
        this.setPasswordSalt(passwordSalt);
        this.setOldPasswordHash(oldPasswordHash);

    }
}