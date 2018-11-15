package de.thd.okb.model.db;

import de.thd.okb.model.dto.SearchProfileDto;
import de.thd.okb.model.profiles.AbstractRole;
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
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author tlang
 * Entity SearchProfile.
 * Maps the table from the datasource to this type.
 */
@Entity
@Table(name = "searchprofile", schema = "public", indexes = {
        @Index(name = "index_searchprofile_description", columnList = "description")
})
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "SearchProfile.abstractRole.jobTypes.studyCourses",
                attributeNodes = {
                        @NamedAttributeNode(
                                value = "searchProfileJobTypeSet",
                                subgraph = "jobType"),
                        @NamedAttributeNode(
                                value = "searchProfileStudyCourseSet",
                                subgraph = "studyCourse"
                        ),
                        @NamedAttributeNode("abstractRole")
                },

                subgraphs = {
                        @NamedSubgraph(
                                name = "jobType",
                                attributeNodes = {
                                        @NamedAttributeNode("jobType")
                                }
                        ),
                        @NamedSubgraph(
                                name = "studyCourse",
                                attributeNodes = {
                                        @NamedAttributeNode("studyCourse")
                                }
                        )
                }
        ),
        @NamedEntityGraph(
                name = "SearchProfile.jobTypes.studyCourses",
                attributeNodes = {
                        @NamedAttributeNode(
                                value = "searchProfileJobTypeSet",
                                subgraph = "jobType"),
                        @NamedAttributeNode(
                                value = "searchProfileStudyCourseSet",
                                subgraph = "studyCourse"
                        )}
        )
})
@NoArgsConstructor
public class SearchProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @NotNull
    private long searchProfileId;

    @NotNull
    @Size(max = 128)
    @Column(nullable = false, length = 128)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private AbstractRole abstractRole;

    @NotNull
    @OneToMany(mappedBy = "searchProfile", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SearchProfileJobType> searchProfileJobTypeSet = new HashSet<>();

    @NotNull
    @OneToMany(mappedBy = "searchProfile", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SearchProfileStudyCourse> searchProfileStudyCourseSet = new HashSet<>();

    @OneToMany(mappedBy = "searchProfile", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SearchProfileCompany> searchProfileCompanySet = new HashSet<>();

    @NotNull
    @Column(nullable = false)
    private boolean isPublic;

    @NotNull
    @Column(nullable = false)
    private boolean emailNotification;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param searchProfileDto a given search profile dto
     */
    public SearchProfile(@NonNull SearchProfileDto searchProfileDto, @NonNull AbstractRole abstractRole,
                         @NonNull Iterable<StudyCourse> studyCourses,
                         @NonNull Iterable<JobType> jobTypes) {
        this();
        this.emailNotification = searchProfileDto.isNotification();
        this.description = searchProfileDto.getDescription();
        this.abstractRole = abstractRole;
        this.isPublic = searchProfileDto.isShowPublic();

        List<SearchProfileStudyCourse> searchProfileStudyCourses = new ArrayList<>();
        studyCourses.forEach((s) -> searchProfileStudyCourses.add(new SearchProfileStudyCourse(this, s)));

        List<SearchProfileJobType> searchProfileJobTypes = new ArrayList<>();
        jobTypes.forEach((j) -> searchProfileJobTypes.add(new SearchProfileJobType(this, j)));

        this.searchProfileStudyCourseSet.addAll(searchProfileStudyCourses);
        this.searchProfileJobTypeSet.addAll(searchProfileJobTypes);
    }

    /**
     * Edits the current instance.
     *
     * @param searchProfileDto a given search profile dto
     * @param studyCourses     given study courses
     * @param jobTypes         given job types
     */
    public void editSearchProfile(@NonNull SearchProfileDto searchProfileDto,
                                  @NonNull Iterable<StudyCourse> studyCourses,
                                  @NonNull Iterable<JobType> jobTypes) {
        this.searchProfileId = searchProfileDto.getSearchProfileId();
        this.emailNotification = searchProfileDto.isNotification();
        this.description = searchProfileDto.getDescription();

        searchProfileStudyCourseSet.clear();
        searchProfileJobTypeSet.clear();

        List<SearchProfileStudyCourse> searchProfileStudyCourses = new ArrayList<>();
        studyCourses.forEach((s) -> searchProfileStudyCourses.add(new SearchProfileStudyCourse(this, s)));

        List<SearchProfileJobType> searchProfileJobTypes = new ArrayList<>();
        jobTypes.forEach((j) -> searchProfileJobTypes.add(new SearchProfileJobType(this, j)));

        this.searchProfileStudyCourseSet.addAll(searchProfileStudyCourses);
        this.searchProfileJobTypeSet.addAll(searchProfileJobTypes);
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
        SearchProfile searchProfile = (SearchProfile) o;
        return searchProfileId == searchProfile.searchProfileId;
    }

    /**
     * Computes a hash for the current instance.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(searchProfileId);
    }
}
