package de.thd.okb.model.dto;

import java.time.LocalDate;

/**
 * Projection interface.
 * Used to project only certain columns.
 * Advantage: Not needed columns won´t be fetched.
 *
 * @author tlang
 */
public interface ExpiredJobOfferProjection {

    /**
     * the JobOffer id.
     *
     * @return long
     */
    long getOfferId();

    /**
     * The short description of the offer.
     *
     * @return String
     */
    String getShortDesc();

    /**
     * The short description of the offer.
     *
     * @return String
     */
    String getEmail();


    /**
     * The valid until date.
     *
     * @return LocalDate
     */
    LocalDate getValidUntilDate();

    /**
     * Printable version of the current interface referance.
     *
     * @return String
     */
    default String prettyPrint() {
        return String.format("Id: %s Description: %s E-Mail: %s", getOfferId(), getShortDesc(), getEmail());
    }

}
