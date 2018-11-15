package de.thd.okb.model.dto;

import de.thd.okb.model.db.StudyCourse;
import de.thd.okb.model.other.StudyCourseEnum;
import de.thd.okb.model.profiles.StaffRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
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
public class
StaffRoleDto extends AbstractRoleDto {

    @Size(max = 2048)
    private String education;

    @Size(max = 2048)
    private String apprenticeship;

    @NotNull
    private String studycourse = StudyCourseEnum.NONE.getValue();

    @Size(max = 2048)
    private String thesis;

    @Size(max = 2048)
    private String internationalExperience;

    @Size(max = 2048)
    private String languageSkills;

    @Size(max = 2048)
    private String itSkills;

    @Size(max = 2048)
    private String specialSkills;

    @Size(max = 2048)
    private String interests;

    @AssertTrue
    private boolean hasAcceptedPrivacyDisclaimer = false;

    /**
     * Overloaded constructor.
     * Builds the given references.
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
    StaffRoleDto(@NotNull long abstractRoleId, @NotNull @Size(max = 128) String firstName,
                 @NotNull @Size(max = 128) String lastName, @NotNull @Size(max = 128)
                 @Email String email, @Size(max = 4096) String aboutMe, @Size(max = 2048) String education,
                 @Size(max = 2048) String apprenticeship, StudyCourse studycourse,
                 @Size(max = 2048) String thesis, @Size(max = 2048) String internationalExperience,
                 @Size(max = 2048) String languageSkills, @Size(max = 2048) String itSkills,
                 @Size(max = 2048) String specialSkills, @Size(max = 2048) String interests) {
        super(abstractRoleId, firstName, lastName, email, aboutMe);
        this.education = education;
        this.apprenticeship = apprenticeship;
        this.studycourse = (studycourse != null) ? studycourse.getCode() : StudyCourseEnum.NONE.getValue();
        this.thesis = thesis;
        this.internationalExperience = internationalExperience;
        this.languageSkills = languageSkills;
        this.itSkills = itSkills;
        this.specialSkills = specialSkills;
        this.interests = interests;
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param staffRole a given staff role
     */
    public StaffRoleDto(StaffRole staffRole) {
        this(staffRole.getAbstractRoleId(), staffRole.getFirstName(), staffRole.getLastName(), staffRole.getEmail(), staffRole.getAboutMe(),
                staffRole.getEducation(), staffRole.getApprenticeship(), staffRole.getStudycourse(), staffRole.getThesis(), staffRole.getInternationalExperience(),
                staffRole.getLanguageSkills(), staffRole.getItSkills(), staffRole.getSpecialSkills(), staffRole.getInterests());
    }
}
