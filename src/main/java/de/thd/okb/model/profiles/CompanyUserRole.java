package de.thd.okb.model.profiles;

import de.thd.okb.model.db.ApplicationUser;
import de.thd.okb.model.db.Company;
import de.thd.okb.model.dto.CompanyUserDto;
import de.thd.okb.model.dto.CompanyUserRoleDto;
import de.thd.okb.model.dto.UserProfileDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.DatatypeConverter;
import java.util.Objects;

/**
 * @author tlang
 * Entity CompanyUserRole.
 * Maps the table from the datasource to this type.
 */
@Entity(name = "CompanyUserRole")
@DiscriminatorValue("CompanyUserRole")
@Getter
@Setter
@NoArgsConstructor
public class CompanyUserRole extends AbstractRole {

    @NotNull
    @Column
    private String passwordHash;

    @NotNull
    @Column
    private String passwordSalt;

    @NotNull
    @Column
    private String oldPasswordHash;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param userProfileDto a given user profile dto
     * @param passwordSalt   a given password salt
     * @param password       a given password
     * @param company        a given company
     */
    public CompanyUserRole(@NonNull UserProfileDto userProfileDto, byte[] passwordSalt, byte[] password, @NonNull Company company) {
        this(userProfileDto, company);
        this.passwordHash = java.util.Base64.getEncoder().encodeToString(password);
        this.passwordSalt = java.util.Base64.getEncoder().encodeToString(passwordSalt);
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param userProfileDto a given user profile dto
     * @param company        a given company
     */
    public CompanyUserRole(@NonNull UserProfileDto userProfileDto, @NonNull Company company) {
        this();
        this.setFirstName(userProfileDto.getFirstName());
        this.setLastName(userProfileDto.getLastName());
        this.setEmail(userProfileDto.getEmail());
        this.company = company;
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param abstractRoleId a given abstract role id
     * @param passwordHash   a given password hash
     * @param passwordSalt   a given password salt
     */
    public CompanyUserRole(long abstractRoleId, String passwordHash, byte[] passwordSalt) {
        this();
        this.setAbstractRoleId(abstractRoleId);
        this.passwordHash = passwordHash;
        this.passwordSalt = DatatypeConverter.printBase64Binary(passwordSalt);
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param applicationUser a given application user
     * @param companyUserDto  a given company user dto
     * @param passwordHash    a given password hash
     * @param passwordSalt    a given password salt
     * @param oldPasswordHash a given old password hash.
     * @param company         a given company
     */
    public CompanyUserRole(@NonNull ApplicationUser applicationUser, @NonNull CompanyUserDto companyUserDto, @NonNull String passwordHash,
                           @NonNull String passwordSalt, @NonNull String oldPasswordHash, @NonNull Company company) {
        this();
        this.syncValues(companyUserDto.getCompanyUserRoleDto(), passwordHash, passwordSalt, oldPasswordHash);
        this.company = company;
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
        return this == o || o instanceof CompanyUserRole && super.equals(o);
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
     * Syncs some values
     *
     * @param abstractRoleId a given abstract role id
     * @param firstName      a given firstname
     * @param lastName       a given lastname
     * @param email          a given email
     * @param aboutMe        given about me
     */
    @Override
    protected void syncValues(long abstractRoleId, @NonNull String firstName, @NonNull String lastName, @NonNull String email, String aboutMe) {
        this.setAbstractRoleId(abstractRoleId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setAboutMe(aboutMe);
    }


    /**
     * Syncs some values.
     *
     * @param companyUserRoleDto a given company user role dto
     * @param passwordHash       a given password hash
     * @param passwordSalt       a given password salt
     * @param oldPasswordHash    a given old password hash
     */
    public void syncValues(@NonNull CompanyUserRoleDto companyUserRoleDto, @NonNull String passwordHash, @NonNull String passwordSalt,
                           @NonNull String oldPasswordHash) {
        this.syncValues(companyUserRoleDto);
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.oldPasswordHash = oldPasswordHash;
    }

    /**
     * Syncs some values.
     *
     * @param companyUserRoleDto a given company user role dto
     */
    public void syncValues(@NonNull CompanyUserRoleDto companyUserRoleDto) {
        this.syncValues(companyUserRoleDto.getAbstractRoleId(), companyUserRoleDto.getFirstName(), companyUserRoleDto.getLastName(), companyUserRoleDto.getEmail(), companyUserRoleDto.getAboutMe());
    }
}