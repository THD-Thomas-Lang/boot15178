package de.thd.okb.model.dto;

import de.thd.okb.model.db.Company;
import lombok.*;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A JobOfferDistance data transfer object (dto)
 *
 * @author tlang
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private long companyId;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String homepageUrl;

    private MultipartFile multipartFile;

    @NotNull
    private boolean disabled;

    @NotNull
    private boolean partner;

    @Valid
    @NotNull
    private AddressDto addressDto = new AddressDto();

    private Iterable<FileAttachmentProjection> fileAttachmentProjections;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param company a given company
     */
    public CompanyDto(@NonNull Company company) {
        this();
        Assert.notNull(company.getAddress(), "Address cannot be null on a company!");
        this.companyId = company.getCompanyId();
        this.name = company.getName();
        this.homepageUrl = company.getHomepageUrl();
        this.disabled = company.isDisabled();
        this.partner = company.isPartner();
        this.addressDto = new AddressDto(company.getAddress());
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param company                   a given company
     * @param fileAttachmentProjections given file attachments
     */
    public CompanyDto(Company company, Iterable<FileAttachmentProjection> fileAttachmentProjections) {
        this(company);
        this.fileAttachmentProjections = fileAttachmentProjections;
    }
}
