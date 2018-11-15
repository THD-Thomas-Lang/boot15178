package de.thd.okb.model.dto;

import de.thd.okb.model.db.JobOfferNewsletter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A JobOffer data transfer object (dto)
 *
 * @author tlang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferNewsletterDto {

    @NotNull
    private JobOfferNewsletter jobOfferNewsletter;

    @NotNull
    @NotEmpty
    private List<String> jobTypes = new ArrayList<>();

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param currentApplicationUser a given application user
     */
    public JobOfferNewsletterDto(String currentApplicationUser) {
        this();
        this.jobOfferNewsletter = new JobOfferNewsletter(currentApplicationUser);
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param jobOfferNewsletter a given job offer newsletter
     */
    public JobOfferNewsletterDto(JobOfferNewsletter jobOfferNewsletter) {
        this(jobOfferNewsletter.getCreatedBy());
        this.jobOfferNewsletter = jobOfferNewsletter;
        this.jobTypes = jobOfferNewsletter.getJobOfferNewsletterJobTypeSet().stream().map(s -> s.getJobType().getCode()).collect(Collectors.toList());
    }
}
