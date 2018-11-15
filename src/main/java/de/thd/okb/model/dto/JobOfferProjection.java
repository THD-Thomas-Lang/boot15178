package de.thd.okb.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Projection interface.
 * Used to project only certain columns.
 * Advantage: Not needed columns won´t be fetched.
 *
 * @author tlang
 */
public interface JobOfferProjection {

    /**
     * Formats the distance as string.
     *
     * @return String
     */
    default String formatDistance() {
        return String.format("%1.2f", getDistance());
    }

    /**
     * the JobOffer id.
     *
     * @return long
     */
    long getOfferId();

    /**
     * the Company id.
     *
     * @return long
     */
    long getCompanyId();

    /**
     * the Company name.
     *
     * @return String
     */
    String getName();

    /**
     * the offer area.
     *
     * @return String
     */
    String getArea();

    /**
     * The city of the offer.
     *
     * @return String
     */
    String getCity();

    /**
     * The country of the offer.
     *
     * @return String
     */
    String getCountry();

    /**
     * The short description of the offer.
     *
     * @return String
     */
    String getShortDesc();

    /**
     * The latitude.
     *
     * @return BigDecimal
     */
    BigDecimal getLat();

    /**
     * The longitude
     *
     * @return BigDecimal
     */
    BigDecimal getLng();

    /**
     * The valid until date.
     *
     * @return LocalDate
     */
    LocalDate getValidFromDate();

    /**
     * The created at date.
     *
     * @return LocalDate
     */
    LocalDate getCreatedAt();

    /**
     * The distance between two points.
     *
     * @return double
     */
    double getDistance();
}
