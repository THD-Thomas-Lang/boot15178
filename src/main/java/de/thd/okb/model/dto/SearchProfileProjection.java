package de.thd.okb.model.dto;

import java.time.LocalDateTime;

/**
 * Projection interface.
 * Used to project only certain columns.
 * Advantage: Not needed columns won´t be fetched.
 *
 * @author tlang
 */
public interface SearchProfileProjection {

    /**
     * the search profile id.
     *
     * @return long
     */
    long getSearchprofileid();

    /**
     * the description.
     *
     * @return long
     */
    String getDescription();

    /**
     * the role´s discriminator code.
     *
     * @return String
     */
    String getDtype();

    /**
     * The created at date time.
     *
     * @return LocalDateTime
     */
    LocalDateTime getCreatedat();

    /**
     * The given email.
     *
     * @return String
     */
    String getEmail();

}
