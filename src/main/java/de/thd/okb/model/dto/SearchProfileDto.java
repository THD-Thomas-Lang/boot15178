package de.thd.okb.model.dto;

import de.thd.okb.model.db.SearchProfile;
import de.thd.okb.model.profiles.AbstractRole;
import lombok.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Company Logo Dto.
 * A Data Transfer Object, used for data exchange.
 *
 * @author tlang
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchProfileDto {

    private long searchProfileId;

    @NotNull
    private String description;

    private LocalDateTime createdAt;

    private String roleType;

    @NotNull
    @Size(min = 1)
    private List<String> jobTypes = new ArrayList<>();

    @NotNull
    @Size(min = 1)
    private List<String> studyCourses = new ArrayList<>();

    private boolean notification;

    private boolean showPublic;

    private AbstractRole abstractRole;

    @AssertTrue
    private boolean hasAcceptedPrivacyDisclaimer = false;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param searchProfileProjection     a given search profile
     * @param jobTypesBySearchProfile     given job type codes
     * @param studyCoursesBySearchProfile given study courses
     */
    public SearchProfileDto(@NonNull SearchProfileProjection searchProfileProjection,
                            @NonNull List<SearchProfileJobTypeStudyCourseDto> jobTypesBySearchProfile,
                            @NonNull List<SearchProfileJobTypeStudyCourseDto> studyCoursesBySearchProfile) {
        this();
        this.searchProfileId = searchProfileProjection.getSearchprofileid();
        this.description = searchProfileProjection.getDescription();
        this.createdAt = searchProfileProjection.getCreatedat();
        this.roleType = searchProfileProjection.getDtype();
        jobTypes.addAll(jobTypesBySearchProfile.stream().map(SearchProfileJobTypeStudyCourseDto::getCode).collect(Collectors.toList()));
        studyCourses.addAll(studyCoursesBySearchProfile.stream().map(SearchProfileJobTypeStudyCourseDto::getCode).collect(Collectors.toList()));
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param searchProfile a given search profile
     */
    public SearchProfileDto(@NonNull SearchProfile searchProfile) {
        this();
        this.searchProfileId = searchProfile.getSearchProfileId();
        this.description = searchProfile.getDescription();
        this.notification = searchProfile.isEmailNotification();
        this.showPublic = searchProfile.isPublic();

        this.jobTypes.addAll(searchProfile.getSearchProfileJobTypeSet().stream().map(s -> s.getJobType().getCode()).collect(Collectors.toList()));
        this.studyCourses.addAll(searchProfile.getSearchProfileStudyCourseSet().stream().map(s -> s.getStudyCourse().getCode()).collect(Collectors.toList()));

        if (searchProfile.getAbstractRole() != null) this.abstractRole = searchProfile.getAbstractRole();
    }

    /**
     * Formats the given job type list.
     *
     * @return String
     */
    public String formatJobTypes() {
        return jobTypes.stream().collect(Collectors.joining("; "));
    }

    /**
     * Formats the given study course list.
     *
     * @return String
     */
    public String formatStudyCourses() {
        return studyCourses.stream().collect(Collectors.joining("; "));
    }

}
