package de.thd.okb.model.dto;

import de.thd.okb.model.db.FileAttachment;

import java.util.Set;

/**
 * Projection interface.
 * Used to project only certain columns.
 * Advantage: Not needed columns won´t be fetched.
 *
 * @author tlang
 */
public interface AttachmentProjection {

    /**
     * the JobOffer id.
     *
     * @return long
     */
    long getOfferId();

    /**
     * A given file attachment set
     *
     * @return Set of FileAttachment
     */
    Set<FileAttachment> getFileAttachmentSet();
}
