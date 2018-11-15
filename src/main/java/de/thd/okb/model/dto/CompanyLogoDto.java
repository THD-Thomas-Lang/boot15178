package de.thd.okb.model.dto;

import lombok.Getter;

/**
 * Company Logo Dto.
 * A Data Transfer Object, used for data exchange.
 *
 * @author tlang
 */
@Getter
public class CompanyLogoDto {

    private String url;
    private String imageFileName;

    /**
     * Overloaded constructor.
     * Builds up the needed references.
     *
     * @param companyLogoProjection a given data projection
     */
    public CompanyLogoDto(CompanyLogoProjection companyLogoProjection) {
        this.url = companyLogoProjection.getHomePageUrl();
        this.imageFileName = companyLogoProjection.getImage();
    }
}
