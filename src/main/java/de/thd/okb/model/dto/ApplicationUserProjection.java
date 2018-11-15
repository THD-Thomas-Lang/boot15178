package de.thd.okb.model.dto;

import de.thd.okb.model.profiles.AbstractRole;

import java.util.List;

/**
 * Projection interface for spring data jpa.
 * Used to project only certain columns.
 * Spring data JPA implements the interface.
 *
 * @author tlang
 */
public interface ApplicationUserProjection {

    /**
     * The given roles
     *
     * @return List of AbstractRoles
     */
    List<AbstractRole> getRoleTypes();

    /**
     * The user id.
     *
     * @return long
     */
    long getApplicationUserId();

    /**
     * The user name.
     *
     * @return String
     */
    String getLogin();


}
