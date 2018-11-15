package de.thd.okb.model.dto;

import de.thd.okb.model.db.Address;
import de.thd.okb.model.db.JobOfferInternal;
import de.thd.okb.model.other.JobTypeEnum;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A JobOfferDistance data transfer object (dto)
 *
 * @author tlang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InternalOfferDto {

    private long internalOfferId;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate validFrom = LocalDate.now();

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate validUntil = LocalDate.now().plusWeeks(1);

    @NotNull
    @NotEmpty
    private List<String> studyCourses;

    @NotNull
    @Size(min = 2)
    private String area;

    @NotNull
    private boolean useHqAddress = true;

    @NotNull
    @Size(max = 64)
    private String occupationScope;

    @NotNull
    private boolean cv = false;

    @NotNull
    private boolean coverletter = false;

    @NotNull
    private boolean gradesheet = false;

    @Size(max = 64)
    private String otherDocuments;

    @NotNull
    @Size(min = 2)
    private String description;

    @NotNull
    private JobTypeEnum jobType;

    @Valid
    private CompanyContactDto companyContactDto;

    private MultipartFile multipartFile;

    @Valid
    @NotNull
    private AddressDto addressDto = new AddressDto();


    /**
     * Overloaded constructor.
     * Builts the needed references.
     *
     * @param address           a given address
     * @param companyContactDto a given company contact dto
     */
    public InternalOfferDto(@NonNull Address address, @NonNull CompanyContactDto companyContactDto) {
        this.addressDto = new AddressDto(address);
        this.companyContactDto = companyContactDto;
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param jobOfferInternal a given internal job offer
     */
    public InternalOfferDto(@NonNull JobOfferInternal jobOfferInternal) {
        this();
        Assert.notNull(jobOfferInternal.getJobOfferInternalStudyCourseSet(), "Study Courses cannot be null!");
        this.internalOfferId = jobOfferInternal.getJobOfferInternalId();
        this.validFrom = jobOfferInternal.getValidFromDate();
        this.validUntil = jobOfferInternal.getValidUntilDate();
        this.area = jobOfferInternal.getArea();
        this.occupationScope = jobOfferInternal.getOccupationScope();
        this.cv = jobOfferInternal.isCv();
        this.coverletter = jobOfferInternal.isCoverletter();
        this.gradesheet = jobOfferInternal.isGradesheet();
        this.otherDocuments = jobOfferInternal.getOtherDocuments();
        this.description = jobOfferInternal.getDescription();
        this.companyContactDto = new CompanyContactDto(jobOfferInternal.getCompanyContact());
        this.jobType = jobOfferInternal.getJobType();
        this.studyCourses = jobOfferInternal.getJobOfferInternalStudyCourseSet().stream().map(s -> s.getStudyCourse().getCode()).collect(Collectors.toList());
    }
}
