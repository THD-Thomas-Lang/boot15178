package de.thd.okb.model.db;

import de.thd.okb.model.dto.AlumniRoleDto;
import de.thd.okb.model.dto.CompanyUserDto;
import de.thd.okb.model.dto.PersonDto;
import de.thd.okb.model.profiles.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author tlang
 * Entity ApplicationUser.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "applicationuser", schema = "public", indexes = {
        @Index(name = "index_applicationuser_username", columnList = "username", unique = true)
})
@NamedEntityGraphs({
        @NamedEntityGraph(name = "ApplicationUser.abstractRoleSet",
                attributeNodes = {
                        @NamedAttributeNode("abstractRoleSet")
                })
})
@Getter
@Setter
@NoArgsConstructor
public class ApplicationUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long applicationUserId;

    @OneToMany(mappedBy = "applicationUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<AbstractRole> abstractRoleSet = new HashSet<>();

    @NotNull
    @Size(max = 128)
    @Column(nullable = false, length = 128, unique = true)
    private String username;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param personDto      a given person dto
     * @param staffOrStudent a given staff or student
     * @param studyCourse    a given study course
     */
    public ApplicationUser(@NonNull PersonDto personDto, boolean staffOrStudent, @NonNull StudyCourse studyCourse) {
        this.username = personDto.getLogin();
        this.abstractRoleSet.add(staffOrStudent ? new StaffRole(personDto, studyCourse, this) : new StudentRole(personDto, studyCourse, this));
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param alumniUserDto   a given alumni user dto
     * @param studyCourse     a given study course
     * @param passwordHash    a given password hash
     * @param passwordSalt    a given password salt
     * @param oldpasswordHash a given old password hash
     */
    public ApplicationUser(@NonNull AlumniRoleDto alumniUserDto, @NonNull StudyCourse studyCourse, @NonNull String passwordHash,
                           @NonNull String passwordSalt, @NonNull String oldpasswordHash) {
        this();
        String username = alumniUserDto.getUsername().trim().toLowerCase();
        this.username = String.format("%s-%s", "al", username);
        this.abstractRoleSet.add(new AlumniRole(this, alumniUserDto, studyCourse, passwordHash, passwordSalt, oldpasswordHash));
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param companyUserDto  a given company user dto
     * @param passwordHash    a given password hash
     * @param passwordSalt    a given password salt
     * @param oldPasswordHash a given old password hash
     */
    public ApplicationUser(@NonNull CompanyUserDto companyUserDto, @NonNull String passwordHash,
                           @NonNull String passwordSalt, @NonNull String oldPasswordHash, @NonNull Company company) {
        this();
        this.username = companyUserDto.getCompanyUserRoleDto().getEmail();
        this.abstractRoleSet.add(new CompanyUserRole(this, companyUserDto, passwordHash, passwordSalt, oldPasswordHash, company));
    }

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
        ApplicationUser applicationUser = (ApplicationUser) o;
        return applicationUserId == applicationUser.applicationUserId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(applicationUserId);
    }

    /**
     * Syncs some values.
     *
     * @param companyUserDto  a given company user dto
     * @param passwordHash    a given password hash
     * @param passwordSalt    a given password salt
     * @param oldPasswordHash a given old password hash
     */
    public void syncValues(@NonNull CompanyUserDto companyUserDto, @NonNull String passwordHash, @NonNull String passwordSalt,
                           @NonNull String oldPasswordHash) {
        this.username = companyUserDto.getCompanyUserRoleDto().getEmail();
        final Optional<AbstractRole> first = this.abstractRoleSet.stream().findFirst();
        if (first.isPresent() && first.get() instanceof CompanyUserRole) {
            ((CompanyUserRole) first.get()).syncValues(companyUserDto.getCompanyUserRoleDto(), passwordHash, passwordSalt, oldPasswordHash);
        }

    }

    /**
     * Syncs some values.
     *
     * @param companyUserDto a given company user dto
     */
    public void syncValues(@NonNull CompanyUserDto companyUserDto) {
        this.username = companyUserDto.getCompanyUserRoleDto().getEmail();
        final Optional<AbstractRole> first = this.abstractRoleSet.stream().findFirst();
        if (first.isPresent() && first.get() instanceof CompanyUserRole) {
            ((CompanyUserRole) first.get()).syncValues(companyUserDto.getCompanyUserRoleDto());
        }

    }
}
