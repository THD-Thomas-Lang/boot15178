package de.thd.okb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Specific implementation of the given interface
 *
 * @author tlang
 */
@Getter
@Setter
public class JobOfferProjectionImpl implements JobOfferProjection {
    private long offerId;
    private long companyId;
    private String area;
    private String name;
    private String city;
    private String country;
    private String shortDesc;
    private BigDecimal lat;
    private BigDecimal lng;
    private LocalDate validFromDate;
    private double distance;
    private LocalDate createdAt;

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param jobOfferProjection A JobOfferProjection interface
     */
    private JobOfferProjectionImpl(JobOfferProjection jobOfferProjection) {
        this.offerId = jobOfferProjection.getOfferId();
        this.companyId = jobOfferProjection.getCompanyId();
        this.name = jobOfferProjection.getName();
        this.city = jobOfferProjection.getCity();
        this.country = jobOfferProjection.getCountry();
        this.shortDesc = jobOfferProjection.getShortDesc();
        this.lat = jobOfferProjection.getLat();
        this.lng = jobOfferProjection.getLng();
        this.validFromDate = jobOfferProjection.getValidFromDate();
    }

    /**
     * Overloaded constructor.
     * Builds the needed references.
     *
     * @param jobOfferProjection A JobOfferProjection interface
     * @param distance           the given distance
     */
    public JobOfferProjectionImpl(JobOfferProjection jobOfferProjection, double distance) {
        this(jobOfferProjection);
        this.distance = distance;
    }

    /**
     * Formats the distance as string.
     *
     * @return String
     */
    public String formatDistance() {
        return String.format("%1.2f", distance);
    }
}