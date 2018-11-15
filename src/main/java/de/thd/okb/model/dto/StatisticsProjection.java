package de.thd.okb.model.dto;

/**
 * Projection interface for spring data jpa.
 * Used to project only certain columns.
 * Spring data JPA implements the interface.
 *
 * @author tlang
 */
public interface StatisticsProjection {

    /**
     * The given count key.
     *
     * @return long
     */
    long getCountKey();

    /**
     * The given count value.
     *
     * @return String
     */
    String getCountValue();
}
