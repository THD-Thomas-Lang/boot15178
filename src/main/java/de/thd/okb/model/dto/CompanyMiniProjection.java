package de.thd.okb.model.dto;

/**
 * Projection interface for spring data jpa.
 * Used to project only certain columns.
 * Spring data JPA implements the interface.
 *
 * @author tlang
 */
public interface CompanyMiniProjection {

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
