package de.thd.okb.model.dto;

/**
 * Projection interface for spring data jpa.
 * Used to project only certain columns.
 * Spring data JPA implements the interface.
 *
 * @author tlang
 */
public interface CompanyProjection {

    /**
     * The company id.
     *
     * @return long
     */
    long getCompanyId();

    /**
     * The company name.
     *
     * @return String
     */
    String getName();

    /**
     * The companies´ address city.
     *
     * @return String
     */
    String getCity();

    /**
     * The companies´ address iso code.
     *
     * @return String
     */
    String getIsoCode();

    /**
     * A job offers count.
     *
     * @return long
     */
    long getCountJobOffers();


    /**
     * if disabled.
     *
     * @return String
     */
    boolean getDisabled();

    /**
     * if partner.
     *
     * @return String
     */
    boolean getPartner();
}
