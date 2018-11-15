package de.thd.okb.model.dto;

/**
 * Projection interface.
 * Used to project only certain columns.
 * Advantage: Not needed columns won´t be fetched.
 *
 * @author tlang
 */
public interface FileAttachmentProjection {

    /**
     * The given property.
     *
     * @return long
     */
    long getAttachmentId();

    /**
     * The given property.
     *
     * @return String
     */
    String getFile();

    /**
     * The given property.
     *
     * @return String
     */
    String getFilename();
}
