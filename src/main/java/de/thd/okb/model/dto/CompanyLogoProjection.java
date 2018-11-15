package de.thd.okb.model.dto;

/**
 * Projection interface for spring data jpa.
 * Used to project only certain columns.
 * Spring data JPA implements the interface.
 *
 * @author tlang
 */
public interface CompanyLogoProjection {

    /**
     * The filename.
     *
     * @return String
     */
    String getImage();

    /**
     * The homepage.
     *
     * @return String
     */
    String getHomePageUrl();
}
