package de.thd.okb.model.dto;

import de.thd.okb.model.db.JobOffer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
public class JobOfferDto {

    @NotNull
    private long jobOfferId;

    @NotNull
    private long companyId;

    @NotNull
    private long contactId;

    @NotNull
    @Size(max = 255)
    private String shortDesc;

    @NotNull
    private boolean useHqAddress = true;

    @NotNull
    private boolean offerPublic = true;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate validFrom = LocalDate.now();

    @NotNull
    private long validForMonths = 6;

    @NotNull
    @Size(max = 8192)
    private String description;

    @Valid
    @NotNull
    private AddressDto addressDto = new AddressDto();

    private String companyContactDescription;

    @NotNull
    @NotEmpty
    private List<String> jobTypes = new ArrayList<>();

    @NotNull
    @NotEmpty
    private List<String> studyCourses = new ArrayList<>();

    private MultipartFile multipartFile;

    /**
     * Overloaded constructor.
     * Builds the needed references
     *
     * @param companyId a given company id
     */
    public JobOfferDto(long companyId) {
        this();
        this.companyId = companyId;
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param jobOffer a given job offer
     */
    public JobOfferDto(@NonNull JobOffer jobOffer) {
        this();
        this.companyId = jobOffer.getCompany().getCompanyId();
        this.jobOfferId = jobOffer.getJobOfferId();
        this.contactId = jobOffer.getCompanyContact().getCompanyContactId();
        this.companyContactDescription = jobOffer.getCompanyContact().getDescription();
        this.shortDesc = jobOffer.getShortDesc();
        this.useHqAddress = jobOffer.isUseHqAddress();
        this.offerPublic = jobOffer.isPublic();
        this.validFrom = jobOffer.getValidFromDate();
        this.validForMonths = jobOffer.getValidForMonths();
        this.description = jobOffer.getDescription();
        this.addressDto = new AddressDto(jobOffer.getAddress());

        this.jobTypes.addAll(jobOffer.getJobOfferJobTypeSet().stream().map(s -> s.getJobType().getCode()).collect(Collectors.toList()));
        this.studyCourses.addAll(jobOffer.getJobOfferStudyCourseSet().stream().map(s -> s.getStudyCourse().getCode()).collect(Collectors.toList()));


    }
}
