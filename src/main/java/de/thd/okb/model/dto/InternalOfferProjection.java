package de.thd.okb.model.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Projection interface for spring data jpa.
 * Used to project only certain columns.
 * Spring data JPA implements the interface.
 *
 * @author tlang
 */
public interface InternalOfferProjection {

    /**
     * The given property.
     *
     * @return long
     */
    long getInternalOfferId();

    /**
     * The given property.
     *
     * @return LocalDate
     */
    LocalDate getValidFrom();

    /**
     * The given property.
     *
     * @return int
     */
    int getValidForMonths();

    /**
     * The given property.
     *
     * @return List of String
     */
    List<String> getStudyCourses();

    /**
     * The given property.
     *
     * @return String
     */
    String getShortDescription();

    /**
     * The given property.
     *
     * @return String
     */
    String getDescription();

    /**
     * The given property.
     *
     * @return long
     */
    long getCompanyContactId();

    /**
     * The given property.
     *
     * @return String
     */
    String getCompanyContactDescription();

    /**
     * The given property.
     *
     * @return String
     */
    String getEmail();

    /**
     * The given property.
     *
     * @return String
     */
    String getPhone();

    /**
     * The given property.
     *
     * @return long
     */
    String getHomepageUrl();
}
