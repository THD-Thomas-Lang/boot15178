package de.thd.okb.model.dto;

/**
 * Projection interface for spring data jpa.
 * Used to project only certain columns.
 * Spring data JPA implements the interface.
 *
 * @author tlang
 */
public interface CompanyContactProjection {

    /**
     * The company contact id.
     *
     * @return String
     */
    long getCompanyContactId();

    /**
     * The description.
     *
     * @return String
     */
    String getDescription();
}
