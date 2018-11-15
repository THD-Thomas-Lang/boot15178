package de.thd.okb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tlang
 * Entity FileAttachment.
 * Maps the table from the datasource to this type.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileAttachmentDto {
    private long fileAttachmentId;
    private String file;
    private String filename;
}
