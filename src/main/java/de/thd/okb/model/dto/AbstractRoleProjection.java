package de.thd.okb.model.dto;

/**
 * Projection interface for spring data jpa.
 * Used to project only certain columns.
 * Spring data JPA implements the interface.
 *
 * @author tlang
 */
public interface AbstractRoleProjection {

    /**
     * The abstract role id.
     *
     * @return long
     */
    long getAbstractRoleId();

    /**
     * The application user id.
     *
     * @return long
     */
    long getApplicationUserId();

    /**
     * The first name.
     *
     * @return String
     */
    String getFirstName();

    /**
     * The last name.
     *
     * @return String
     */
    String getLastName();

    /**
     * The email.
     *
     * @return String
     */
    String getEmail();

    /**
     * The given study course.
     *
     * @return String
     */
    String getStudyCourse();
}
