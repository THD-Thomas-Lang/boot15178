package de.thd.okb.model.dto;

/**
 * Projection interface for spring data jpa.
 * Used to project only certain columns.
 * Spring data JPA implements the interface.
 *
 * @author tlang
 */
public interface SearchProfileJobTypeStudyCourseProjection {

    /**
     * The filename.
     *
     * @return String
     */
    String getCode();

    /**
     * The homepage.
     *
     * @return String
     */
    long getSearchProfileId();
}
