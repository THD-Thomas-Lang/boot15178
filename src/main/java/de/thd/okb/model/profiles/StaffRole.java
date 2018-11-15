package de.thd.okb.model.profiles;

import de.thd.okb.model.db.ApplicationUser;
import de.thd.okb.model.db.SearchProfile;
import de.thd.okb.model.db.StudyCourse;
import de.thd.okb.model.dto.PersonDto;
import de.thd.okb.model.dto.StaffRoleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

/**
 * @author tlang
 * Entity StaffRole.
 * Maps the table from the datasource to this type.
 */
@Entity(name = "StaffRole")
@DiscriminatorValue("StaffRole")
@Getter
@Setter
@NoArgsConstructor
public class StaffRole extends AbstractRole {

    @Column(length = 2048)
    @Size(max = 2048)
    private String education;

    @Column(length = 2048)
    @Size(max = 2048)
    private String apprenticeship;

    @ManyToOne
    @NotNull
    private StudyCourse studycourse;

    @Column(length = 2048)
    @Size(max = 2048)
    private String thesis;

    @Column(length = 2048)
    @Size(max = 2048)
    private String internationalExperience;

    @Column(length = 2048)
    @Size(max = 2048)
    private String languageSkills;

    @Column(length = 2048)
    @Size(max = 2048)
    private String itSkills;

    @Column(length = 2048)
    @Size(max = 2048)
    private String specialSkills;

    @Column(length = 2048)
    @Size(max = 2048)
    private String interests;

    @OneToMany(mappedBy = "abstractRole", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SearchProfile> searchProfileSet;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param personDto       a given person dto
     * @param studyCourse     a given study course
     * @param applicationUser a given application user
     */
    public StaffRole(@NonNull PersonDto personDto, @NonNull StudyCourse studyCourse, @NonNull ApplicationUser applicationUser) {
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
        return this == o || o instanceof StaffRole && super.equals(o);
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
    protected void syncValues(long abstractRoleId, @NonNull String firstName, @NonNull String lastName,
                              @NonNull String email, String aboutMe) {
        this.setAbstractRoleId(abstractRoleId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setAboutMe(aboutMe);
    }


    /**
     * Syncs some values.
     *
     * @param abstractRoleId          a given abstract role id
     * @param firstName               a given first name
     * @param lastName                a given last name
     * @param email                   a given email
     * @param aboutMe                 a given about me
     * @param education               a given education
     * @param apprenticeship          a given apprenticeship
     * @param studycourse             a given study course
     * @param thesis                  a given thesis
     * @param internationalExperience given international experience
     * @param languageSkills          given language skills
     * @param itSkills                given it skills
     * @param specialSkills           given special skills
     * @param interests               given interests
     */
    public void syncValues(@NotNull long abstractRoleId, @NotNull @Size(max = 128) String firstName,
                           @NotNull @Size(max = 128) String lastName, @NotNull @Size(max = 128)
                           @Email String email, @Size(max = 4096) String aboutMe, @Size(max = 2048) String education,
                           @Size(max = 2048) String apprenticeship, StudyCourse studycourse,
                           @Size(max = 2048) String thesis, @Size(max = 2048) String internationalExperience,
                           @Size(max = 2048) String languageSkills, @Size(max = 2048) String itSkills,
                           @Size(max = 2048) String specialSkills, @Size(max = 2048) String interests) {

        this.syncValues(abstractRoleId, firstName, lastName, email, aboutMe);
        this.education = education;
        this.apprenticeship = apprenticeship;
        this.studycourse = studycourse;
        this.thesis = thesis;
        this.internationalExperience = internationalExperience;
        this.languageSkills = languageSkills;
        this.itSkills = itSkills;
        this.specialSkills = specialSkills;
        this.interests = interests;
    }

    /**
     * Syncs some values.
     *
     * @param staffRoleDto a given staff role dto
     * @param studyCourse  a given study course
     */
    public void syncValues(@NonNull StaffRoleDto staffRoleDto, @NonNull StudyCourse studyCourse) {
        this.syncValues(staffRoleDto.getAbstractRoleId(), staffRoleDto.getFirstName(), staffRoleDto.getLastName(),
                staffRoleDto.getEmail(), staffRoleDto.getAboutMe(), staffRoleDto.getEducation(), staffRoleDto.getApprenticeship(), studyCourse,
                staffRoleDto.getThesis(), staffRoleDto.getInternationalExperience(), staffRoleDto.getLanguageSkills(),
                staffRoleDto.getItSkills(), staffRoleDto.getSpecialSkills(), staffRoleDto.getInterests());
    }
}